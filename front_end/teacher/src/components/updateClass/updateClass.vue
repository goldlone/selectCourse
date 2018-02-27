<template>
  <div>
    <el-table
      :data="classes"
      style="width: 100%">
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <div v-for="i in props.row.stages" class="stage">
              <el-form-item label="期数:">
                <span>{{ i.stage }}</span>
              </el-form-item>
              <el-form-item label="教室:">
                <span>{{ i.classroom }}</span>
              </el-form-item>
              <el-form-item label="老师:">
                <span>{{ i.teacher }}</span>
              </el-form-item>
              <el-form-item label="开课时间:">
                <span>{{ i.startDateTime }}</span>
              </el-form-item>
              <el-form-item label="结课时间:">
                <span>{{ i.endDateTime }}</span>
              </el-form-item>
            </div>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column
        label="课程编号"
        prop="no">
      </el-table-column>
      <el-table-column
        label="课程名"
        prop="name">
      </el-table-column>
      <el-table-column
        label="总期数"
        prop="stages.length">
      </el-table-column>
      <el-table-column
        label="课时"
        prop="time">
      </el-table-column>
      <el-table-column
      label="操作">
        <template slot-scope="scope">
          <el-button @click="rewriteClass(scope.row)" type="text" size="small">修改课程</el-button>
        </template>
      </el-table-column>
    </el-table>


    <!--表单-->
    <el-dialog :visible.sync="updateClassFlag">
      <el-row>
        <el-col :span="18" :offset="2">
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
            <div class="time">
              <div v-for="item in tempClass.plus" class="chooseTime">
                <span>第{{item.stage}}期 </span>
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
                  <el-input v-model="item.classroom"></el-input>
                </el-form-item>
                <el-form-item label="教师">
                  <el-input v-model="item.teacher"></el-input>
                </el-form-item>
              </div>
            </div>
            <el-form-item label="所占学时">
              <el-input v-model="tempClass.time"></el-input>
            </el-form-item>
            <el-form-item label="上课学生权限">
              <el-select v-model="tempClass.powers" multiple>
                <el-option v-for="item in powerOption" :key="item.value" :label="item.label" :value="item.value">

                </el-option>
              </el-select>
            </el-form-item>
            <el-button @click="update()">提交更改</el-button>
          </el-form>
        </el-col>
      </el-row>
    </el-dialog>

  </div>
</template>

<script>
  let config = require("../../config/config");
  const ip = config.ip;
  let moment = require("moment");
  let $ = require("jquery");
  let _ = require("underscore");
    export default {
        name: "update-class",
        data(){
          return {
            tempCourseNo:1,
            beforeChange:[],//未修改前的状态
            stageLength:0,
            classes:[],//所有
            updateClassFlag:false,
            tempClass:{
              stages:[]
            },
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
            ],
            stageOption:[]
          }
        },
      watch:{
         stageLength:function () {
             if(this.tempClass.plus.length < this.stageLength){
               let length = this.tempClass.plus.length;
               let temp = this.stageLength - this.tempClass.plus.length;
               for(let i = 0 ;i< temp ;i++){
                 this.tempClass.plus.push({
                   stage:i+1+length,
                   name:`第${i+1+length}期`,
                   value:i+1,
                   selectClassTime:[],
                   classTime:[],
                   teacher:"",
                   classroom:""
                 })
               }
             }else{
               this.tempClass.plus = this.tempClass.plus.slice(0,this.stageLength);
             }
         }
      },
      methods:{
        update(){
          let self = this;
          this.$confirm('此操作将修改该课程, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.tempClass.plus.forEach(function (ele) {
              ele.startDateTime = ele.classTime[0];
              ele.endDateTime = ele.classTime[1];
              ele.startDateTime = moment(ele.startDateTime).format("YYYY-MM-D HH:mm:ss")
              ele.endDateTime = moment(ele.endDateTime).format("YYYY-MM-D HH:mm:ss")
            })
            this.$http.post(`http://${ip}/course/update`,
              self.tempClass
            ).then((response)=>{
              if(response.data.code == 1001){
                self.$message({
                  type: 'info',
                  message: '修改课程成功'
                });
                self.updateClassFlag = false;
              }else{
                self.$message.error("修改课程失败");
              }
            }).catch((err)=>{
                self.$message.error("修改课程失败，请检查网络状况");
            })
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '取消修改课程'
            });
          });
        },
        rewriteClass(row){
          /***
           * 修改课程
           */
          let self = this;
          // self.tempCourseNo = row.no;
          $.post(`http://${ip}/course/infoOfUpdate`,{
            courseNo:row.no
          },function (response) {
            console.log(response)
            self.tempClass = response.data;
            self.stageLength = response.data.plus.length;
            for(let i = 0;i<self.tempClass.plus.length;i++){
              self.tempClass.plus[i].classTime = [self.tempClass.plus[i].startDateTime,self.tempClass.plus[i].endDateTime];
            }
            self.tempClass.courseNo = row.no;
            self.updateClassFlag = true;
          })
        }
      },
      beforeMount:function () {
          let self = this;
          let temp = new Set();
          for(let i = 0;i<9;i++){
              this.stageOption.push({
                value:i+1,
                label:i+1
              })
          }

          $.post(`http://${ip}/course/allInfo`,function (response) {
            if(response.code == 1001){
              for(let i = 0;i < response.data.length;i++){
                temp.add(response.data[i].no);
              }


              for (let j of temp){
                self.classes.push({
                  no:j,
                  stages:[]
                })
              }

              for(let i = 0;i< response.data.length;i++){
                for(let j = 0;j<self.classes.length;j++){
                  if(self.classes[j].no == response.data[i].no){
                    response.data[i].startDateTime = moment(response.data[i].startDateTime).format("YYYY/MM/DD");
                    response.data[i].endDateTime = moment(response.data[i].endDateTime).format("YYYY/MM/DD")
                    self.classes[j].stages.push(response.data[i]);
                  }
                }
              }

              for(let i = 0;i < self.classes.length;i++){
                self.classes[i].name = self.classes[i].stages[0].name;
                self.classes[i].time = self.classes[i].stages[0].time;
              }

            }
          })
      }
    }
</script>

<style scoped>
  .stage{
    padding-top: 20px;
    border-bottom: 1px solid rgba(0,0,0,0.2);
  }
  .stage:last-child{
    /*padding-top: 20px;*/
    border-bottom: 0px;
  }

  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }
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
