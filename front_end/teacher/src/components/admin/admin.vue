<template>
  <div>
    <el-tabs v-model="activeName">
      <el-tab-pane label="查看管理员" name="view">
        <el-table
          :data="administrator"
          stripe
          style="width: 100%">
          <el-table-column
            prop="no"
            label="工号"
            width="350">
          </el-table-column>
          <el-table-column
            prop="name"
            label="姓名"
            width="350">
          </el-table-column>
          <el-table-column
            prop="school"
            label="基层党组织"
            width="350">
          </el-table-column>
          <el-table-column
            prop="power"
            label="权限"
            width="350">
          </el-table-column>
          <el-table-column
            fixed="right"
            label="操作"
            width="200">
            <template slot-scope="scope">
              <el-row>
                <el-col><el-button type="text" size="small" @click="deleteSpecialAdmin(scope.row)">删除</el-button></el-col>
              </el-row>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
      <el-tab-pane label="添加管理员" name="add">
        <el-row>
          <el-form ref="tempAdminInfo" :model="tempAdminInfo" label-width="140px">
            <el-form-item label="工号">
              <el-input v-model="tempAdminInfo.no"></el-input>
            </el-form-item>
            <el-form-item label="姓名">
              <el-input v-model="tempAdminInfo.name"></el-input>
            </el-form-item>
            <el-form-item label="基层党组织">
              <el-select v-model="tempAdminInfo.schoolNo" placeholder="请选择基层党组织">
                <el-option
                  v-for="item in parties"
                  :key="item.no"
                  :label="item.name"
                  :value="item.no">
                </el-option>
              </el-select>
            </el-form-item>
            <el-col :span="12" :offset="10">
              <el-button @click="insertAdmin()">提交</el-button>
            </el-col>
          </el-form>
        </el-row>
      </el-tab-pane>
    </el-tabs>

  </div>
</template>

<script>
  let config = require("../../config/config");
  const ip = config.ip;
  let moment = require("moment");
  let $ = require("jquery");
  let _ = require("underscore");
    export default {
        name: "admin",
      data(){
          return {
            tempAdminInfo:{

            },
            parties:[],
            administrator:[],
            options:[{
              value:0,
              key:"超级管理员"
            },
              {
                value:1,
                key:"基层管理员"
              }],
            activeName:"view"
          }
      },
      methods:{
        deleteSpecialAdmin(row){
          let self = this;
          this.$confirm('此操作将永久删除该管理员, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(()=>{
            $.post(`http://${ip}/admin/delete`,{
              no:row.no
            },function(response){
              if(response.code == 1001){
                self.$message("删除管理员成功")
                $.post(`http://${ip}/admin/list`,function(response){
                  if(response.code == 1001){
                    console.log(response.data);
                    self.administrator = response.data;
                    for(let i = 0;i < response.data.length;i++){
                      self.options.forEach(function (e) {
                        if(e.value == response.data[i].power){
                          self.administrator[i].power = e.key;
                        }
                      })
                    }
                  }else{
                    self.$message.error("请求管理员列表失败");
                  }
                })
              }else{
                self.$message.error(`删除管理员失败，原因：${response.msg}`)
              }
            })
          })
        },

        insertAdmin(){
          let self = this;
          $.post(`http://${ip}/admin/add`,{
            no:self.tempAdminInfo.no,
            name:self.tempAdminInfo.name,
            schoolNo:self.tempAdminInfo.schoolNo
          },function (response) {
            if(response.code == 1001){
              self.$message("添加管理员成功");
            }else{
              self.$message.error(`添加管理员失败，原因：${response.msg}`);
            }
          })
        }
      },
      beforeMount:function(){
          if(this.$store.power !== 0){
            this.$message.error("您没有权限查看此页面");
            this.$router.push("/");
            return;
          }
          let self = this;
        $.post(`http://${ip}/getSchools`,function (response) {
          if(response.code == 1001){
            self.parties = response.data;
          }else{
            console.log("获取基层党组织信息失败")
          }
        })
          $.post(`http://${ip}/admin/list`,function(response){
            if(response.code == 1001){
              console.log(response.data);
              self.administrator = response.data;
              for(let i = 0;i < response.data.length;i++){
                self.options.forEach(function (e) {
                  if(e.value == response.data[i].power){
                    self.administrator[i].power = e.key;
                  }
                })
              }
            }else{
              self.$message.error("请求管理员列表失败");
            }
          })
      }
    }
</script>

<style scoped>

</style>
