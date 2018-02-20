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
          prop="power"
          label="权限编号"
          width="120">
        </el-table-column>
        <el-table-column
          fixed="right"
          label="操作"
          width="100">
          <template slot-scope="scope">
            <el-button @click="showUpdateInfo = true;selectStudentInfo = scope.row" type="text" size="small">修改</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-row>

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
          <el-form-item label="权限">
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
  </div>
</template>

<script>
    import ElButton from "element-ui/packages/button/src/button";
    let config = require("../../config/config");
    const ip = config.ip;
    let moment = require("moment");
    let $ = require("jquery");
    let _ = require("underscore");
    export default {
      components: {ElButton},
      name: "view-student",
      data(){
        return {
          showUpdateInfo:false,//控制修改信息栏是否打开
          students:[],//存储学生信息
          parties:[],
          tempStudentParty:"",//所选择的学生要修改的基层党组织信息
          selectParty:"",//所选择的基层党组织信息
          selectStudentInfo:{},//所选择的学生信息
          tempStudentInfo:{},//临时存储学生信息
          powerOption:[
            {
              value:0,
              label:"超级管理员"
            },
            {
              value:1,
              label:"基层党委组织超级管理员"
            },
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
          $.post(`http://${ip}/stu/schoolInfo`,{
            schoolNo:self.selectParty
          },function (response) {
            if(response.code == 1001){
              self.students = response.data;
              self.students.forEach((ele)=>{
                ele.beActivistDate = moment(ele.beActivistDate).format("YYYY/MM/D");
                ele. applyDate = moment(ele. applyDate).format("YYYY/MM/D");
                ele.beDevelopDate = moment(ele.beDevelopDate).format("YYYY/MM/D");
              })
            }else{
              if(self.selectParty!=undefined){
                alert("获取学生信息失败")
              }
            }
          })
        },
        /**
         *
         */
        open:function () {

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
      watch:{
        selectParty:function () {
          this.getAllStudents();
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
  .selectParty{
    padding-top:50px;
  }
  .studentTable{
    margin-top: 20px;
  }
</style>
