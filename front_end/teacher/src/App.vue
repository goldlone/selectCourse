<template>
  <div id="app">
    <el-container>
      <el-header>
        <el-menu class="el-menu-demo" mode="horizontal">
          <el-menu-item index="1">管理端</el-menu-item>
          <a href="/logout" class="signout">注销</a>
          <span class="headName">尊敬的{{power}}</span>
        </el-menu>
      </el-header>
      <el-container>
        <el-aside width="300px">
          <left-nav></left-nav>
        </el-aside>
        <el-main>
          <router-view/>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
  let config = require("./config/config");
  const ip = config.ip;
  let moment = require("moment");
  let $ = require("jquery");
  let _ = require("underscore");
  import leftNav from "./components/leftNav/leftNav"
export default {
  name: 'App',
  components:{
    leftNav
  },
  data(){
    return {
      power:''
    }
  },
  beforeCreate:function () {
    this.$http.post(`http://${ip}/power`)
      .then(response=>{
        if(response.data.code == 1001){
          console.log(response.data.data);
        }
        this.$store.power = response.data.data;
        console.log(this.$store.power);
        if(this.$store.power == 0){
          this.power = "超级管理员"
        }else if(this.$store.power == 1){
          this.power = "基层管理员"
        }
      })
  }
}
</script>

<style>
  .headName{
    float: right;
    position:relative;
    top:15px;
    right: 10px;
  }
  .signout{
    float: right;
    position:relative;
    top:15px;
    color:red;
  }
</style>
