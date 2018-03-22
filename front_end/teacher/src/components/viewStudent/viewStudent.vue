<template>
  <div>
    <el-tabs v-model="activeName">
      <el-tab-pane label="按基层党组织查看" name="school">
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
          <el-col :span="8">
            <el-select v-model="selectBatch" placeholder="请选择批次" class="selectParty">
              <el-option
                v-for="item in batch"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-col>
          <el-col :span="8" :offset="16">
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6">
            <span>学号</span>
            <el-input v-model="filterID"></el-input>
          </el-col>
          <el-col :span="6" :offset="2">
            <span>姓名</span>
            <el-input v-model="filterName"></el-input>
          </el-col>
          <el-col :span="1" :offset="1">
            <span style="position:relative;top:25px;">筛选</span>
          </el-col>
          <el-col :span="2">
            <el-button type="danger" @click="end()" style="position:relative;top:20px;">结束该批次学员</el-button>
          </el-col>
        </el-row>
        <el-row>
          <el-table
            :data="showStudents"
            border
            style="width: 100%"
            class="studentTable">
            <el-table-column
              fixed
              prop="no"
              label="学号"
              width="120">
            </el-table-column>
            <el-table-column
              prop="name"
              label="姓名"
              width="120">
            </el-table-column>
            <el-table-column
              prop="gender"
              label="性别"
              width="120">
            </el-table-column>
            <el-table-column
              prop="nation"
              label="民族"
              width="120">
            </el-table-column>
            <el-table-column
              prop="birth"
              label="出生日期"
              width="120">
            </el-table-column>
            <el-table-column
              prop="type"
              label="学生类别"
              width="120">
            </el-table-column>
            <el-table-column
              prop="grade"
              label="年级"
              width="120">
            </el-table-column>
            <el-table-column
              prop="position"
              label="职务"
              width="120">
            </el-table-column>
            <el-table-column
              prop="applyDate"
              label="申请入党时间"
              width="120">
            </el-table-column>
            <el-table-column
              prop="beActivistDate"
              label="定为积极分子时间"
              width="120">
            </el-table-column>
            <el-table-column
              prop="beDevelopDate"
              label="定位发展对象初步入选时间"
              width="120">
            </el-table-column>
            <el-table-column
              prop="powerLabel"
              label="权限"
              width="120">
            </el-table-column>
            <!--<el-table-column-->
              <!--prop="power"-->
              <!--label="权限编号"-->
              <!--width="100">-->
            <!--</el-table-column>-->
            <el-table-column
              fixed="right"
              label="操作"
              width="200">
              <template slot-scope="scope">
                <el-row>
                  <el-col :span="4"><el-button @click="showUpdateInfo = true;selectStudentInfo = scope.row" type="text" size="small">修改</el-button></el-col>
                  <el-col :span="6" :offset="1"><el-button type="text" size="small" @click="viewChooseClass(scope.row)">查看选课信息</el-button></el-col>
                  <el-col :span="6" :offset="6"><el-button type="text" size="small" @click="deleteSpecialStudent(scope.row)">删除</el-button></el-col>
                </el-row>
              </template>
            </el-table-column>
          </el-table>
          <el-row>
            <el-col :span="8" :offset="9" class="pagination">
              <el-pagination
                background
                :page-size="20"
                @current-change="handleCurrentChange"
                layout="prev, pager, next"
                :total="students.length">
              </el-pagination>
            </el-col>
          </el-row>
        </el-row>
      </el-tab-pane>
      <el-tab-pane label="学生查询" name="search">
        <el-row>
          <el-col :span="6">
            <span>学号</span>
           <el-input v-model="stuID">

           </el-input>
          </el-col>
          <el-col :span="6" :offset="2">
            <span>姓名</span>
            <el-input v-model="stuName">

            </el-input>
          </el-col>
          <el-col :span="8">
            <el-button class="searchSpecialStu" @click="findSpecialStudent">搜索</el-button>
          </el-col>
        </el-row>
        <el-row>
          <el-table
            :data="searchSpecialStudent"
            border
            style="width: 100%"
            class="studentTable">
            <el-table-column
              fixed
              prop="no"
              label="学号"
              width="120">
            </el-table-column>
            <el-table-column
              prop="name"
              label="姓名"
              width="120">
            </el-table-column>
            <el-table-column
              prop="gender"
              label="性别"
              width="120">
            </el-table-column>
            <el-table-column
              prop="nation"
              label="民族"
              width="120">
            </el-table-column>
            <el-table-column
              prop="birth"
              label="出生日期"
              width="120">
            </el-table-column>
            <el-table-column
              prop="type"
              label="学生类别"
              width="120">
            </el-table-column>
            <el-table-column
              prop="grade"
              label="年级"
              width="120">
            </el-table-column>
            <el-table-column
              prop="position"
              label="职务"
              width="120">
            </el-table-column>
            <el-table-column
              prop="applyDate"
              label="申请入党时间"
              width="120">
            </el-table-column>
            <el-table-column
              prop="beActivistDate"
              label="定为积极分子时间"
              width="120">
            </el-table-column>
            <el-table-column
              prop="beDevelopDate"
              label="定位发展对象初步入选时间"
              width="120">
            </el-table-column>
            <el-table-column
              prop="powerLabel"
              label="身份"
              width="120">
            </el-table-column>
            <!--<el-table-column-->
              <!--prop="power"-->
              <!--label="权限编号"-->
              <!--width="100">-->
            <!--</el-table-column>-->
            <el-table-column
              fixed="right"
              label="操作"
              width="200">
              <template slot-scope="scope">
                <el-row>
                  <el-col :span="4"><el-button @click="showUpdateInfo = true;selectStudentInfo = scope.row" type="text" size="small">修改</el-button></el-col>
                  <el-col :span="6" :offset="1"><el-button type="text" size="small" @click="viewChooseClass(scope.row)">查看选课信息</el-button></el-col>
                  <el-col :span="6" :offset="6"><el-button type="text" size="small" @click="deleteSpecialStudent(scope.row)">删除</el-button></el-col>
                </el-row>
              </template>
            </el-table-column>
          </el-table>
        </el-row>
      </el-tab-pane>
    </el-tabs>

    <el-dialog title="修改信息" :visible.sync="showUpdateInfo">
        <el-form ref="tempStudentInfo" :model="tempStudentInfo" label-width="140px">
          <el-form-item label="学号">
            <el-input v-model="tempStudentInfo.no" :disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="姓名">
            <el-input v-model="tempStudentInfo.name"></el-input>
          </el-form-item>
          <el-form-item label="性别">
            <el-radio-group v-model="tempStudentInfo.gender" size="medium">
              <el-radio-button label="男" ></el-radio-button>
              <el-radio-button label="女"></el-radio-button>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="基层党组织">
            <el-select v-model="tempStudentParty" placeholder="请选择基层党组织">
              <el-option
                v-for="item in parties"
                :key="item.no"
                :label="item.name"
                :value="item.no">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="民族">
            <el-input v-model="tempStudentInfo.nation"></el-input>
          </el-form-item>
          <el-form-item label="出生日期">
            <el-date-picker
              v-model="tempStudentInfo.birth"
              align="right"
              type="date"
              placeholder="选择出生日期">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="学生类别">
            <el-input v-model="tempStudentInfo.type"></el-input>
          </el-form-item>
          <el-form-item label="年级">
            <el-input v-model="tempStudentInfo.grade"></el-input>
          </el-form-item>
          <el-form-item label="职务">
            <el-input v-model="tempStudentInfo.position"></el-input>
          </el-form-item>
          <el-form-item label="申请入党时间">
            <el-date-picker
              v-model="tempStudentInfo.applyDate"
              align="right"
              type="date"
              placeholder="选择申请入党日期">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="定为发展对象时间">
            <el-date-picker
              v-model="tempStudentInfo.beDevelopDate"
              align="right"
              type="date"
              placeholder="选择定为发展对象日期">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="定为积极分子时间">
            <el-date-picker
              v-model="tempStudentInfo.beActivistDate"
              align="right"
              type="date"
              placeholder="选择定为积极分子日期">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="身份">
            <el-select v-model="tempStudentInfo.power">
              <el-option v-for="item in powerOption" :key="item.value" :label="item.label" :value="item.value">

              </el-option>
            </el-select>
          </el-form-item>
          <el-row>
            <el-col :span="12" :offset="10">
              <el-button @click="updateSpecialStudentInfo()">提交修改</el-button>
            </el-col>
          </el-row>
          提示：权限编号 0-代表超级管理员、1-基层党组织管理员、2-党员、3-预备党员、4-入党积极分子、5-发展对象
        </el-form>
    </el-dialog>


    <!--查看学生选课-->
    <el-dialog :visible.sync="showChooseClass">
      <el-table :data="tableData" type="flex">
        <el-table-column label="课程编号" prop="courseNo">

        </el-table-column>
        <el-table-column label="座位编号" prop="seatNo">

        </el-table-column>
        <el-table-column label="课程名称" prop="courseName">

        </el-table-column>
        <el-table-column label="教室" prop="classroom">

        </el-table-column>
        <el-table-column label="教师" prop="teacher">

        </el-table-column>

        <!--奇怪的问题，不会更新-->
        <!--<el-table-column label="开课时间" prop="startDateTimeLabel">-->
          <!--<template slot-scope="scope">-->
            <!--{{scope.row.startDateTIme}}-->
          <!--</template>-->
        <!--</el-table-column>-->
        <!--<el-table-column label="结课时间" prop="endDateTimeLabel">-->
          <!--<template slot-scope="scope">-->
            <!--{{scope.row.startDateTIme}}-->
          <!--</template>-->
        <!--</el-table-column>-->
        <el-table-column label="课程期数" prop="stage">

        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button @click="cancelChoose(scope.row)" type="text" size="small">取消选课</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>
  </div>
</template>

<script>
    import ElButton from "element-ui/packages/button/src/button";
    import ElTabPane from "element-ui/packages/tabs/src/tab-pane";
    import ElInput from "element-ui/packages/input/src/input";
    let config = require("../../config/config");
    const ip = config.ip;
    let moment = require("moment");
    let $ = require("jquery");
    let _ = require("underscore");
    export default {
      components: {
        ElInput,
        ElTabPane,
        ElButton},
      name: "view-student",
      data(){
        return {
          showPage:0,//展示的页数
          showStudents:[],//被展示的学生
          selectBatch:"",//选中的批次
          tempStuNoCancelFlag:"",//取消学生选课临时变量
          batch:[],//存储批次
          showChooseClass:false,//控制查看学生选课信息栏是否打开
          showUpdateInfo:false,//控制修改信息栏是否打开
          students:[],//存储学生信息
          parties:[],
          tempStudentParty:"",//所选择的学生要修改的基层党组织信息
          selectParty:"",//所选择的基层党组织信息
          selectStudentInfo:{},//所选择的学生信息
          tempStudentInfo:{},//临时存储学生信息
          activeName:"school",
          searchSpecialStudent:[],//查找特定的学生
          stuID:"",//特定学生的学号
          stuName:"",//特定学生的姓名
          filterID:"",//筛选学生的学号
          filterName:"",//筛选学生的姓名
          filterBeforeStudentInfo:[],//筛选前学生信息
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
          tableData:[]//学生选课信息
        }
      },
      methods:{
        handleCurrentChange:function(val){
            this.showPage = val;
        },
        viewChooseClass:function (row) {
          /***
           * 查看某个学生选课
           * */
          let self = this;
          self.tempStuNoCancelFlag = row.no;
          console.log(self.tempStuNoCancelFlag);
          $.post(`http://${ip}/course/stuSelectStatus`,{
            stuNo:row.no
          },function (response) {
            if(response.code == 1001){
              self.tableData = response.data;
              self.tableData.startDateTimeLabel = moment(self.tableData.startDateTime).format("YYYY/MM/DD hh:mm:ss")
              self.tableData.endDateTimeLabel = moment(self.tableData.endDateTime).format("YYYY/MM/DD hh:mm:ss")
            }else{
              self.$message.error("获取学生选课信息失败")
            }
          })
          this.tableData.startDateTime = moment(this.tableData.startDateTime).format("YYYY/MM/DD hh:mm:ss")
          this.tableData.endDateTime = moment(this.tableData.endDateTime).format("YYYY/MM/DD hh:mm:ss")
          console.log(this.tableData.startDateTime);
          setTimeout(function () {
            self.showChooseClass = true;
          },100)
        },
        end(){
          this.$confirm('此操作将结束当前批次学生, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(()=>{
            this.$http.post(`http://${ip}/stu/endCourse`)
              .then(response=>{
                if(response.data.code == 1001){
                  this.$message("成功结束当前批次学生")
                }else{
                  this.$message.error(`结束当前批次学生失败，原因：${response.data.msg}`);
                }
              }).catch(err=>{
              this.$message.error(`结束当前批次学生失败，原因：${err}`);
            })
          }).catch(err=>{
            console.log(err);
          })
        },
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
        },
        deleteSpecialStudent(row){
          /***
           * 删除特定的学生
           * */

          let self = this;
          this.$confirm('此操作将永久删除该学生, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            $.post(`http://${ip}/stu/delete`,{
              no:row.no
            },function (response) {
              if(response.code == 1001){
                self.$message({
                  type: 'info',
                  message: '删除学生成功'
                });
                self.students = [];
                self.getAllStudents();
              }else{
                self.$message.error("something wrong");
              }
            })
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '取消删除该学生'
            });
          });
        },
        cancelChoose(row){
          let self = this;
          this.$confirm('此操作将取消该学生选课, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            $.post(`http://${ip}//course/admin/cancel`,{
              courseNo:row.courseNo,
              stuNo:self.tempStuNoCancelFlag
            },function (response) {
              if(response.code == 1001){
                self.$message({
                  type: 'success',
                  message: '取消选课成功!'
                });
                self.showChooseClass = false;
              }else{
                self.$message({
                  type: 'info',
                  message: '取消选课失败'
                });
                self.showChooseClass = false;
              }
            })
          }).catch(() => {
            // this.$message({
            //   type: 'info',
            //   message: ''
            // });
          });
        },
        filter(){
          /***
           * 筛选出符合条件的学生
           * */
        },
        updateShowPage(){
          /**
           *  更新显示页面
           * */
          if(this.showPage*20 > this.students.length){
            this.showStudents = this.students.slice((this.showPage-1)*20,this.students.length);
          }else
            this.showStudents = this.students.slice((this.showPage-1)*20,this.showPage*20);
          console.log(`------------------------------------>showPage:${this.showPage}`);
          console.log(`------------------------------------>showStudent:${this.showStudents}`);
          console.log(`------------------------------------>student:${this.students}`);
        },
        updateSpecialStudentInfo:function(){
          /**
           *  修改某一个特定学生的信息
           * */
          console.log(this.tempStudentInfo)
          let self = this;
          this.tempStudentInfo.schoolNo = this.tempStudentParty;
          this.tempStudentInfo.birth = moment(this.tempStudentInfo.birth).format("YYYY/MM/DD")
          this.tempStudentInfo.applyDate = moment(this.tempStudentInfo.applyDate).format("YYYY/MM/DD")
          this.tempStudentInfo.beDevelopDate = moment(this.tempStudentInfo.beDevelopDate).format("YYYY/MM/DD")
          this.tempStudentInfo.beActivistDate = moment(this.tempStudentInfo.beActivistDate).format("YYYY/MM/DD")
          $.post(`http://${ip}/stu/updateInfoByAdmin`,self.tempStudentInfo,(response)=> {
            if(response.code == 1001){
              // self.selectStudentInfo = response.data;
              self.$message("修改学生信息成功");
              self.showUpdateInfo = false;
              self.getAllStudents();
            }else{
              self.$message.error(`修改学生信息失败`);
              console.log(response.msg);
              self.showUpdateInfo = false;
            }
          })
        },
        getAllStudents:function () {
          let self = this;
          if(self.selectBatch!=="" && self.selectParty !== ""){
            $.post(`http://${ip}/stu/schoolInfo`,{
              schoolNo:self.selectParty,
              batch:self.selectBatch
            },function (response) {
              if(response.code == 1001){
                self.students = response.data;
                self.beforeFilter = response.data;
                self.students.forEach((ele)=>{
                  /**
                   *  格式化日期
                   * */
                  ele.birth = moment(ele.birth).format("YYYY/MM/D");
                  ele.beActivistDate = moment(ele.beActivistDate).format("YYYY/MM/D");
                  ele. applyDate = moment(ele. applyDate).format("YYYY/MM/D");
                  ele.beDevelopDate = moment(ele.beDevelopDate).format("YYYY/MM/D");

                  /**
                   *
                   *   将权限信息格式化
                   * */

                  for(let i = 0;i < self.powerOption.length;i++){
                    if(ele.power == self.powerOption[i].value){
                      ele.powerLabel = self.powerOption[i].label;
                    }
                  }
                })
               self.updateShowPage();
              }else{
                if(self.selectParty!=undefined){
                  alert("获取学生信息失败")
                }
              }
            })
          }
        },
        /**
         *
         */
        open:function () {

        },
        findSpecialStudent(){
            let self = this;
            this.searchSpecialStudent = [];//每次搜索清空存储数组
            console.log(self.stuID)
            //当学号存在时，按照学号查询，当学号不存在时，按照姓名查询
            if(this.stuID != ""){
              $.post(`http://${ip}/stu/infoByNo`,{
                stuNo:self.stuID
              },function (response) {
                console.log(response.code)
                if(response.code == 1001){
                  if(response.data == null){
                    self.$message.error("不存在当前学生")
                  }else{
                    self.searchSpecialStudent.push(response.data)
                    self.searchSpecialStudent.forEach((ele)=>{
                      /**
                       *  格式化日期
                       * */
                      ele.birth = moment(ele.birth).format("YYYY/MM/D");
                      ele.beActivistDate = moment(ele.beActivistDate).format("YYYY/MM/D");
                      ele. applyDate = moment(ele. applyDate).format("YYYY/MM/D");
                      ele.beDevelopDate = moment(ele.beDevelopDate).format("YYYY/MM/D");

                      /**
                       *
                       *   将权限信息格式化
                       * */

                      for(let i = 0;i < self.powerOption.length;i++){
                        if(ele.power == self.powerOption[i].value){
                          ele.powerLabel = self.powerOption[i].label;
                        }
                      }
                    })
                  }
                }else{
                  self.$message.error("查询失败，请检查网络状况")
                }
              })
            }else{
              if(this.stuName != ""){
                $.post(`http://${ip}/stu/infoByName`,{
                  name:self.stuName
                },function (response) {
                  if(response.code == 1001){
                    if(response.data == null){
                      self.$message.error("不存在当前学生")
                    }else{
                      // response.msg.map(function (ele) {
                      //   self.searchSpecialStudent.push(ele)
                      // })
                      for(let i = 0;i<response.data.length;i++){
                        self.searchSpecialStudent.push(response.data[i])
                      }
                      self.searchSpecialStudent.forEach((ele)=>{
                        /**
                         *  格式化日期
                         * */
                        ele.birth = moment(ele.birth).format("YYYY/MM/D");
                        ele.beActivistDate = moment(ele.beActivistDate).format("YYYY/MM/D");
                        ele. applyDate = moment(ele. applyDate).format("YYYY/MM/D");
                        ele.beDevelopDate = moment(ele.beDevelopDate).format("YYYY/MM/D");

                        /**
                         *
                         *   将权限信息格式化
                         * */

                        for(let i = 0;i < self.powerOption.length;i++){
                          if(ele.power == self.powerOption[i].value){
                            ele.powerLabel = self.powerOption[i].label;
                          }
                        }
                      })
                    }
                  }else{
                    self.$message.error("查询失败，请检查网络状况")
                  }
                })
              }else{
                this.$message.error("请输入学号或姓名")
              }
            }
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

        $.post(`http://${ip}/stu/batch`,function (response) {
          if(response.code == 1001){
            response.data.forEach(function (e) {
              self.batch.push(
                {
                  label:e,
                  value:e
                }
              )
            })
          }
        })
      },
      watch:{
        showPage:function () {
          console.log(this.students);
          console.log(this.students.slice(0,this.students.length));
          if(this.showPage*20 > this.students.length){
            this.showStudents = this.students.slice((this.showPage-1)*20,this.students.length);
          }else
            this.showStudents = this.students.slice((this.showPage-1)*20,this.showPage*20);
        },
        selectParty:function () {
          this.students = [];
          this.getAllStudents();
          this.showPage = 1;
        },
        selectBatch:function () {
          this.students = [];
          this.getAllStudents();
          this.showPage = 1;
        },
        filterID:function () {
          if(this.filterID  == "" && this.filterName == ""){
            console.log("clean");
            this.students = this.beforeFilter;
            this.updateShowPage();
          }else{
            let temp = [];
            for(let i = 0;i < this.beforeFilter.length;i++){
              if(this.beforeFilter[i].no.indexOf(`${this.filterID}`) >=0){
                temp.push(this.beforeFilter[i]);
              }
            }
            this.students = temp;
            this.updateShowPage();
          }
        },
        filterName:function () {
          if(this.filterID  == "" && this.filterName == ""){
            console.log("clean");
            this.students = this.beforeFilter;
            this.updateShowPage();
          }else{
            let temp = [];
            for(let i = 0;i < this.beforeFilter.length;i++){
              console.log(this.filterName+"!!")
              console.log(this.beforeFilter[i].name)
              console.log(this.beforeFilter[i].name.indexOf(`${this.filterName}`))
              if(this.beforeFilter[i].name.indexOf(`${this.filterName}`) >=0){
                temp.push(this.beforeFilter[i]);
              }
            }
            this.students = temp;
            this.updateShowPage();
          }
        },
        selectStudentInfo:function(){
          this.tempStudentInfo = _.clone(this.selectStudentInfo);
          // console.log(this.selectParty)
          let temp = this.selectParty;
          this.tempStudentParty= temp;
          delete this.tempStudentInfo.school;
        }
      }
    }
</script>

<style scoped>
  .pagination{
    margin-top: 20px;
  }
  .searchSpecialStu{
    margin-top:25px;
    margin-left:30px;
  }
  .studentTable{
    margin-top: 20px;
  }
</style>
