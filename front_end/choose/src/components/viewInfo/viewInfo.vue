<template>
    <div>
      <el-row>
        <el-col :span="18" :offset="3" type="flex">
          <el-form label-width="90px" :model="form">
            <el-form-item label="学号">
              <el-input :disabled="true" v-model="form.no"></el-input>
            </el-form-item>
            <el-form-item label="姓名">
              <el-input v-model="form.name"></el-input>
            </el-form-item>
            <el-form-item label="年级">
              <el-select  placeholder="请选择年级" v-model="form.grade">
                <el-option label="2016" value="2016"></el-option>
                <el-option label="2017" value="2017"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="基层党组织">
              <el-select  placeholder="请选择学院" v-model="form.school">
                <el-option v-for="a in localParties"  v-bind:label="a.name" v-bind:value="a.no" ></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="专业">
              <el-input v-model="form.major"></el-input>
            </el-form-item>
            <el-form-item label="性别">
              <el-radio-group v-model="form.gender">
                <el-radio label="男" value="male"></el-radio>
                <el-radio label="女" value="female"></el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="身份名称">
              <el-input v-model="form.identity" :disabled="true"></el-input>
            </el-form-item>
          </el-form>
        </el-col>
        <el-col :span="3">

        </el-col>
      </el-row>
      <el-row>
        <el-col :span="6" :offset="12">
          <el-button type="danger" @click="dialogVisible = true">修改信息</el-button>
        </el-col>
      </el-row>


      <el-dialog
        title="警告"
        :visible.sync="dialogVisible"
        width="30%">
        <span>确定修改您的个人信息吗？</span>
        <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="changeInfo()">确 定</el-button>
  </span>
      </el-dialog>
    </div>
</template>

<script>
    let $ = require("jquery");
    let moment = require("moment");
    const config = require("../../config/configs");
    export default {
        name: "view-info",
        data(){
          return{
            form:{},
            localParties:[{
              name:"计算机",
              no:"1"
            },{
              name:"生物",
              no:"2"
            }],
            dialogVisible:false
          }
        },
        methods: {
          changeInfo() {
            $.post(``,function (response) {
              if(response.code == 1001){
                this.$store.state.studentState = this.form;
              }else{
                self.$message.error("修改学生信息失败");
              }
            })
            this.dialogVisible = false;
          }
        },
      beforeMount:function () {
        /**
         * 获取学生的信息
         * @type {beforeMount}
         */
          let self = this;
        $.post(`http://${config.ip}/stu/info`,function (response) {
          if(response.code == 1001){
            console.log(response.data)
            self.$store.state.studentState = response.data;
            self.form = self.$store.state.studentState
          }else{
            self.$message.error('获取学生信息失败，请检查您的网络状况');
          }
        })
        $.post(`http://${config.ip}/getSchools`,function(response){
          if(response.code == 1001){
            self.localParties = response.data;
          }else{
            self.$message.error("获取基层党组织信息失败")
          }
        });
      }
    }
</script>

<style scoped>

</style>
