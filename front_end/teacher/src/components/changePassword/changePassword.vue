<template>
  <div>
    <h2>修改密码</h2>
    <!--<el-row>-->
      <!--<el-col :span="4" class="title">-->
        <!--<span>修改密码</span>-->
      <!--</el-col>-->
    <!--</el-row>-->
    <el-row>
      <el-col :span="12" :offset="6">
        <span>旧密码</span>
        <el-input v-model="oldPassword" type="password"></el-input>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12" :offset="6">
        <span>新密码</span>
        <el-input v-model="newPassword" type="password">新密码</el-input>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12" :offset="6">
        <span>再次输入新密码</span>
        <el-input v-model="reNewPassword" type="password">新密码</el-input>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="6" :offset="10" style="margin-top: 20px">
        <el-button type="danger" @click="inputNewPassword">修改密码</el-button>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  let util = require("../../util/utils");
  let $ = require("jquery");
  let conifg = require("../../config/config.js");
  let ip = conifg.ip;
  export default {
    name: "change-password",
    data(){
      return {
        oldPassword:"",
        newPassword:"",
        reNewPassword:""
      }
    },
    methods:{
      inputNewPassword(){
        let self = this;

        this.$confirm('此操作将修改密码, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(()=>{
          if(self.oldPassword == ""|| self.newPassword == ""|| self.reNewPassword == ""){
            return  self.$message.error("请完整输入表单");
          }
          if(self.newPassword != self.reNewPassword){
            return self.$message.error("请输入相同的新密码");
          }
          $.post(`http://${ip}/updateAdminPassword`,{
            stuNo:'123',
            password:self.oldPassword,
            newPassword:self.newPassword,
          },function (response) {
            util.redict(response);
            if(response.code == 1001){
              self.$message("修改密码成功");
            }else if(response.code == 2002){
              self.$message.error("原始密码错误");
            }else{
              self.$message.error("修改密码失败");
            }
          })
        }).catch(()=>{

        })
      }
    }
  }
</script>

<style scoped>
  h2{
    font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
    font-size:26px;
    font-weight: lighter;
    margin-bottom: 30px;
  }
  .title{
    margin-top: 50px;
    margin-bottom: 50px;
  }
</style>
