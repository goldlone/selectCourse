<template>
  <div>

    <el-tabs v-model="activeName">
      <el-tab-pane v-for="s in stages" v-bind:label="s.label" v-bind:name="s.label">
        <el-row :gutter="5">
          <el-col :span="12" v-for="c in s.classes"  type="flex">
            <el-card class="box-card" style="margin-top:20px;">
              <div slot="header" class="clearfix">
                <span>{{c.name}}</span>
                <el-button style="float: right; padding: 3px 0" type="text" :key="c.no" v-on:click="feedback(c.no,c.stage)">反馈此课程的上课状况</el-button>
              </div>
              <div>
                <p>教室:{{c.classroom}}</p>
                <p>授课教师:{{c.teacher}}</p>
                <p>课时数:{{c.time}}</p>
                <p>期数:{{c.stage}}</p>
                <p>开始上课时间:{{c.startDateTime}}</p>
                <p>结束上课时间:{{c.endDateTime}}</p>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </el-tab-pane>
    </el-tabs>





    <!--修改到课情况-->
    <el-dialog  :visible.sync="feedBackFlag" title ="修改到课情况">
      <el-row>
        <el-col :span="18" :offset="5">
          <el-transfer v-model="absentStudent" :data="student"     :titles="['到课学生', '未到课学生']" filterable></el-transfer>
        </el-col>
      </el-row>
      <el-row class="updateFeedBack">
        <el-col :span="8">
          <span>学生总人数{{studentLength}}</span>
          <span>/缺席人数{{absentStudent.length}}</span>
        </el-col>
        <el-col :span="8" :offset="2">
          <el-button @click="sureFlag = true">提交到课情况</el-button>
        </el-col>
      </el-row>
    </el-dialog>

    <el-dialog :visible.sync="sureFlag" title="确定修改到课状况吗？">
      <el-row>
        <el-col :offset="9" >
          <el-button @click="sendFeedBack">确认</el-button>
          <el-button @click="sureFlag = false">取消</el-button>
        </el-col>
      </el-row>
    </el-dialog>
  </div>
</template>

<script>
    import ElSpinner from "element-ui/packages/spinner/src/spinner";
    let ip = require("../../config/config").ip;
    let moment = require("moment");
    let $ = require("jquery");
    let classArray = [];//用来存放课程的数组
    let maxStage = 0;//用来存放最大课程期数的变量
    export default {
      components: {ElSpinner},
      name: "feed-back",
        data(){
          return {
              student:[{
                value:"1",
                label:"张三"
              }],
              absentStudent:[],
              feedBackFlag:false,
              classes:[],
              activeName:"第1期",
              stages:[{
                label:"第1期",
                name:"1",
                classes:[

                ]
              },{
                label:"第二期",
                name:"2",
                classes:[

                ]
              },{
                label:"第三期",
                name:"3",
                classes:[

                ]
              }],
            selectNO:"",//选中的课程编号
            selectStage:"",//选中的期数
            sureFlag:false//确认信息的标记
          }
        },
      methods:{
        sendFeedBack(){
          let self = this;
          console.log(this.absentStudent);
          console.log(this.selectNo)
          this.sureFlag = false;
          // $.post(`http://${ip}/course/feedback`,{
          //   stu:self.absentStudent,
          //   courseNo:self.selectNo
          // },function (response) {
          //   if(response.code == 1001){
          //     self.$message("反馈学生到课状况成功");
          //   }else{
          //     self.$message.error("反馈学生到课信息失败");
          //   }
          // })

          this.$http.post(`http://${ip}/course/feedback`,{
            stu:self.absentStudent,
            courseNo:self.selectNo
          }).then((response)=>{
            console.log(response.data)
            if(response.data.code == 1001){
              self.$message("反馈到课信息成功");
            }else{
              self.$message.error("反馈到课信息失败");
            }
          }).catch(err=>{
            console.log(err);
          })

        },
        feedback(no,stage){
          /***
           * 反馈上课情况
           */
          let self = this;
          this.selectNo = no;
          this.selectStage = stage;
          $.post(`http://${ip}/course/selectStatus`,{
            courseNo:no,
            stage:stage
          },function (response) {
            self.student = [];
            if(response.code == 1001){
              console.log("1001");
              response.data.forEach(function (ele) {
                self.student.push({
                  value:ele.stuNo,
                  label:ele.stuName
                })
              })
              self.feedBackFlag = true;
            }else{
              self.$message.error("获取学生信息出错")
            }
          })
        }
      },
      computed:{
          studentLength:function () {
            return this.student.length;
          }
      },
      beforeMount:function(){
        let self = this;
        self.stages = [];
          let result;
        $.post(`http://${ip}/course/end`,function(response){
          if(response.code == 1001){
            result = response.data;
            response.data.forEach(function (ele) {
              ele.startDateTime = moment(ele.startDateTime).format("YYYY/MM/D,HH:mm:ss")
              ele.endDateTime = moment(ele.endDateTime).format("YYYY/MM/D,HH:mm:ss")
            })
            self.classes = response.data;
            maxStage = result[0].stage;
            for(let i = 0 ;i < result.length;i++){
              if(result[i].stage>maxStage){
                maxStage = result[i].stage;//求出最大的期数
              }
            }
            for(let j = 0;j<maxStage;j++){
              self.stages.push({
                label:`第${j+1}期`,
                name:j+1,
                classes:[]
              })
            }
            // let flag = 0;//计数
            setTimeout(function () {

              for(let x=0;x<self.classes.length;x++){
                // console.log(x)
                for(let y = 0;y<self.stages.length;y++){
                  // console.log(x.stage);
                  // console.log(self.stages[y].name)
                  if(self.classes[x].stage == self.stages[y].name){

                    self.stages[y].classes.push(
                      self.classes[x]
                    )
                  }
                }
              }
            },100)
          }else{
            result = false;
          }
        });
        setTimeout(console.log(self.stages),1000)
      }
    }
</script>
<style scoped>
    .updateFeedBack{
      margin-top:30px;
    }
</style>
