<template>
  <div>
    <el-table :data="tableData" type="flex">
      <el-table-column label="课程编号" prop="courseNo">

      </el-table-column>
      <el-table-column label="座位编号" prop="seatNo">

      </el-table-column>
      <el-table-column label="课程名称" prop="name">

      </el-table-column>
      <el-table-column label="教室" prop="classroom">

      </el-table-column>
      <el-table-column label="教师" prop="teacher">

      </el-table-column>
      <el-table-column label="开课时间" prop="startTime">

      </el-table-column>
      <el-table-column label="结课时间" prop="endTime">

      </el-table-column>
      <el-table-column label="课程期数" prop="stage">

      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button @click="cancelChoose(scope.row)" type="text" size="small">取消选课</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog
      title="确认"
      :visible.sync="cancelSure"
      width="30%">
      <span>确定取消选择这门课程吗？</span>
      <span slot="footer" class="dialog-footer">
    <el-button @click="cancelSure = false">取 消</el-button>
    <el-button type="primary" @click="makeSureCancel()">确 定</el-button>
  </span>
    </el-dialog>
  </div>
</template>

<script>
  let $ = require("jquery");
  let conifg = require("../../config/configs")
  let mount = require("moment");
    export default {
        name: "modify-info",
        data(){
          return {
            cancelCourseNo:0,
            cancelCourseStage:0,
            tableData:[],
            cancelSure:false
          }
        },
      methods:{
        getAllClass(){
          let self = this;
          $.post(`http://${conifg.ip}/course/myCourse`,{

          },function (response) {
            let resultArray = [];
            let result = response.data;
            result.forEach(function(ele){
              let obj = {
                courseNo:ele.courseNo,
                name:ele.stuName,
                seatNo:ele.seatNo,
                classroom:ele.classroom,
                stage:ele.stage,
                teacher:ele.teacher,
                startTime:mount(ele.startDateTime).format("YYYY/MM/D,HH:mm:ss"),
                endTime:mount(ele.endDateTime).format("YYYY/MM/D,HH:mm:ss")
              }
            self.tableData.push(obj);
            })
          })
        },
        cancelChoose(row){
          this.cancelSure = true;
          let {courseNo,stage,no} = row;
          this.cancelCourseNo = courseNo;
          this.cancelCourseStage = stage;
        },
        makeSureCancel(){
          let self = this;
          $.post(`http://${conifg.ip}/course/cancel`,{
            courseNo:self.cancelCourseNo,
            stage:self.cancelCourseStage
          })
          self.tableData = [];
          setTimeout(function(){
            self.getAllClass()
          },100)
          self.cancelSure = false;
        }
      },
      mounted:function () {
        this.getAllClass()
      }
    }
</script>

<style scoped>

</style>
