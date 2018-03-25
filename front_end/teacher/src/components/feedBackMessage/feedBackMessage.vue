<template>
  <div>
    <h2>管理反馈信息</h2>
    <el-table
      :data="feedMsg"
      stripe
      style="width: 100%">
      <el-table-column
        prop="id"
        label="id"
        sortable
        width="100">
      </el-table-column>
      <el-table-column
        prop="content"
        label="反馈信息"
        width="200">
      </el-table-column>
      <el-table-column
        prop="publicTime"
        label="申请时间"
        sortable
        width="200">
      </el-table-column>
      <el-table-column
        prop="publicer"
        label="申请人"
        width="200">
      </el-table-column>
      <el-table-column
        prop="isDeal"
        label="是否被解决"
        sortable
        width="200">
      </el-table-column>
      <el-table-column
        prop="dealMan"
        label="解决人"
        width="200">
      </el-table-column>
      <el-table-column
        prop="dealTime"
        sortable
        label="问题解决时间"
        width="200">
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="200">
        <template slot-scope="scope">
          <el-row>
            <el-col><el-button type="text" size="small" @click="dealFeedBack(scope.row)">解决</el-button></el-col>
          </el-row>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
  let util = require("../../util/utils");
  let config = require("../../config/config");
  const ip = config.ip;
  let $ = require("jquery");
  let moment = require("moment");
  let _ = require("underscore");
    export default {
        name: "feedBackMessage",
        data(){
          return {
            feedMsg:[]
          }
        },
        methods:{
          dealFeedBack(row){
            let self = this;
            $.get(`http://${ip}/stu/dealFeedback`,{
              id:row.id
            },function (response) {
              if(response.code == 1001){
                self.$message("成功处理反馈信息");
                self.$http.post(`http://${ip}/stu/getFeedback`)
                  .then(res=>{
                    if(res.data.code == 1001){
                      self.feedMsg = res.data.data;
                      console.log(self.feedMsg);
                      self.feedMsg.forEach(e=>{
                        e.publicTime = moment(e.publicTime).format("YYYY/MM/DD hh:mm:ss");
                        e.dealTime = moment(e.dealTime).format("YYYY/MM/DD hh:mm:ss");
                        if(e.isDeal == 0){
                          e.isDeal = "未被解决";
                        }else{
                          e.isDeal = "已经被解决";
                        }
                        console.log(e.publicTime);
                      })
                    }else{
                      self.$message(`获取反馈信息失败，失败原因：${res.data.msg}`);
                    }
                  })
              }else{
                this.$message.error(`处理反馈信息失败，原因：${response.msg}`);
              }
            })
          }
        },
        beforeMount:function(){
          this.$http.post(`http://${ip}/stu/getFeedback`)
            .then(res=>{
              if(res.data.code == 1001){
                this.feedMsg = res.data.data;
                console.log(this.feedMsg);
                this.feedMsg.forEach(e=>{
                  e.publicTime = moment(e.publicTime).format("YYYY/MM/DD hh:mm:ss");
                  e.dealTime = moment(e.dealTime).format("YYYY/MM/DD hh:mm:ss");
                  if(e.isDeal == 0){
                    e.isDeal = "未被解决";
                  }else{
                    e.isDeal = "已经被解决";
                  }
                  console.log(e.publicTime);
                })
              }else{
                this.$message(`获取反馈信息失败，失败原因：${res.data.msg}`);
              }
            })

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
</style>
