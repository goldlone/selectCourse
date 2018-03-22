<template>
  <div>
    <span>发布课程</span>
    <el-row>
      <el-col :span="18">
        <el-form label-width="140px">
          <el-form-item label="课程名称">
            <el-input v-model="tempClass.name"></el-input>
          </el-form-item>
          <el-form-item label="期数">
            <el-select v-model="stageLength">
              <el-option v-for="item in stageOption" :key="item.value" :label="item.label" :value="item.value">

              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="所占学时">
            <el-input v-model="tempClass.time"></el-input>
          </el-form-item>
          <el-form-item label="上课学生权限">
            <el-select v-model="tempClass.power" multiple>
              <el-option v-for="item in powerOption" :key="item.value" :label="item.label" :value="item.value">

              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="开课选课时间">
            <el-date-picker
              v-model="tempClass.selectTime"
              type="datetimerange"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期">
            </el-date-picker>
          </el-form-item>
          <div class="time">
            <div v-for="item in stages" class="chooseTime">
              <span>{{item.name}} </span>
              <el-form-item label="开课时间">
                <el-date-picker
                  v-model="item.classTime"
                  type="datetimerange"
                  range-separator="至"
                  start-placeholder="开始日期"
                  end-placeholder="结束日期">
                </el-date-picker>
              </el-form-item>
              <el-form-item label="教室">
                <el-input v-model="item.classroom" :disabled="true"></el-input>
              </el-form-item>
              <el-form-item label="教师">
                <el-input v-model="item.teacher"></el-input>
              </el-form-item>
            </div>
          </div>
        </el-form>

        <el-row>
          <el-col :offset="12" :span="12">
            <el-button @click="releaseNewClass()">发布课程</el-button>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>
<script>
  let config = require("../../config/config");
  const ip = config.ip;
  let $ = require("jquery");
  let moment = require("moment");
  /**
   * 课程名
   * 期数（数字）
   * 教室
   * 教师
   * 开始上课时间
   * 上课结束时间
   * 所占学时
   * 选择权限（多选） ---身份有 党员 预备党员 入党积极分子 发展对象
   */
  let util = require("../../util/utils");

  export default {
        name: "release-class",
        data(){
          return {
            stages:[{
              name:`第1期`,
              value:1,
              selectClassTime:[],
              classTime:[],
              teacher:"",
              classroom:"文科楼三层报告厅",
              stage:1
            }],
            tempClass:{
              power:[]
            },//用来存储新建的课程信息,
            stageOption:[],
            stageLength:1,
            powerOption:[
              {
                value:2,
                label:"党员"
              },
              {
                value:3,
                label:"预备党员"
              },
              {
                value:4,
                label:"入党积极分子"
              },
              {
                value:5,
                label:"发展对象"
              }
            ]
          }
        },
        beforeUpdate:function () {

        },
        methods:{
          releaseNewClass(){
              // console.log(this.stages)
            let self = this;
            this.stages.forEach(function (ele) {
              ele.startDateTime = ele.classTime[0];
              ele.endDateTime = ele.classTime[1];
              ele.startDateTime = moment(ele.startDateTime).format("YYYY-MM-D HH:mm:ss")
              ele.endDateTime = moment(ele.endDateTime).format("YYYY-MM-D HH:mm:ss")
            })
            this.$http.post(`http://${ip}/course/public`,{
              name:self.tempClass.name,
              startSelectDateTime : moment(self.tempClass.selectTime[0]).format("YYYY-MM-D HH:mm:ss"),
              endSelectDateTime : moment(self.tempClass.selectTime[1]).format("YYYY-MM-D HH:mm:ss"),
              time:self.tempClass.time,
              powers:self.tempClass.power,
              plus:self.stages
            }).then((res)=>{
              util.redict(res.data);
              if(res.data.code === 1001 ){
                self.$message("发布课程成功");
              }else{
                if(res.data.code === 3001){
                  self.$message.error(res.data.msg);
                }else
                  self.$message.error(res.data.msg);
              }
            })
          }
        },
        watch:{
            stageLength:function () {
                this.stages = [];
                for(let i = 0;i < this.stageLength;i ++){
                  this.stages.push({
                    stage:i+1,
                    name:`第${i+1}期`,
                    value:i+1,
                    selectClassTime:[],
                    classTime:[],
                    teacher:"",
                    classroom:"文科楼三层报告厅"
                  })
                }
            console.log(this.stages)
          }
        },
       beforeMount:function () {
         for(let i = 0;i<9;i++){
           this.stageOption.push({
             value:i+1,
             label:i+1
           })
         }
       }
    }
</script>

<style scoped>
  .chooseTime:nth-child(n){
    padding-top:15px;
    margin-top:10px;
    margin-bottom:10px;
    /*border-top:1px rgba(0,0,0,0.1) solid;*/
    border-bottom: 1px rgba(0,0,0,0.1) solid;
  }
  .chooseTime:first-child{
    padding-top:20px;
    margin-top:10px;
    margin-bottom:10px;
    border-top:1px rgba(0,0,0,0.1) solid;
    border-bottom: 1px rgba(0,0,0,0.1) solid;
  }
</style>
