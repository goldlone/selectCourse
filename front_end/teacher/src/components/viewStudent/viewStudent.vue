<template>
  <div>
    <el-row>
      <el-col :span="8">
        <el-select v-model="selectParty" placeholder="请选择基层党组织" class="selectParty">
          <el-option
            v-for="item in parties"
            :key="item.no"
            :label="item.name"
            :value="item.no">
          </el-option>
        </el-select>
      </el-col>
      <el-col :span="8"></el-col>
      <el-col :span="8"></el-col>
    </el-row>
    <el-row>
      <el-table
        :data="students"
        border
        style="width: 100%"
      class="studentTable">
        <el-table-column
          fixed
          prop="date"
          label="日期"
          width="150">
        </el-table-column>
        <el-table-column
          prop="name"
          label="姓名"
          width="120">
        </el-table-column>
        <el-table-column
          prop="province"
          label="省份"
          width="120">
        </el-table-column>
        <el-table-column
          prop="city"
          label="市区"
          width="120">
        </el-table-column>
        <el-table-column
          prop="address"
          label="地址"
          width="300">
        </el-table-column>
        <el-table-column
          prop="zip"
          label="邮编"
          width="120">
        </el-table-column>
        <el-table-column
          fixed="right"
          label="操作"
          width="100">
          <template slot-scope="scope">
            <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
            <el-button type="text" size="small">编辑</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-row>
  </div>
</template>

<script>
    import ElButton from "element-ui/packages/button/src/button";
    let config = require("../../config/config");
    const ip = config.ip;
    let $ = require("jquery");
    export default {
      components: {ElButton},
      name: "view-student",
      data(){
        return {
          students:[],//存储学生信息
          parties:[],
          selectParty:"",//所选择的基层党组织信息
          selectPartyTemp:""//临时存储所选择的基层党组织信息
        }
      },
      methods:{
        getSpecialSchoolStudents:function (schoolNo) {

          /**
           * 获取某个基层党组织的学生信息
           */
          let self = this;
          $.post(`http://${ip}/stu/schoolInfo`,{
            schoolNo:schoolNo
          },function (response) {
            if(response.code == 1001){
              self.students = response.data;
            }else{
              console.log("加载学院信息失败")
            }
          })
        }
      },
      beforeMount:function () {

        /**
         * 获取基层党组织信息
         * @type {beforeMount}
         */
           let self  = this;
          $.post(`http://${ip}/getSchools`,function (response) {
            if(response.code == 1001){
              self.parties = response.data;
            }else{
              console.log("获取基层党组织信息失败")
            }
          })
      },
      updated:function () {
        /**
         * 每当改变学院时，重新获取学生信息
         * @type {updated}
         */
        let self = this;
        /**
         * 如果临时变量和所选择变量不符的话，进行新的请求
         */
        if(self.selectPartyTemp!=self.selectParty){
          $.post(`http://${ip}/stu/schoolInfo`,{
            schoolNo:self.selectParty
          },function (response) {
            if(response.code == 1001){
              // console.log(response.data);
              // if()
              self.students = response.data;
              self.selectPartyTemp = self.selectParty;
            }else{
              if(self.selectParty!=undefined){
                alert("获取学生信息失败")
              }
            }
          })
        }
      }
    }
</script>

<style scoped>
  .selectParty{
    padding-top:50px;
  }
  .studentTable{
    margin-top: 20px;
  }
</style>
