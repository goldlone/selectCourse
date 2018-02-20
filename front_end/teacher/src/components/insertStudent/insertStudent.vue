<template>
  <el-tabs v-model="activeName">
    <el-tab-pane label="模板录入" name="templete">
      <el-row>
        <el-col :span="8" class="upload">
          <el-button class="downloadExcel"><a href="http://www.goldlone.cn/sc/stu/exportModel
">点击下载模板</a></el-button>
          <el-upload
            class="upload-demo"
            action="http://www.goldlone.cn/sc/stu/add"
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :before-remove="beforeRemove"
            multiple
            :limit="3"
            :on-exceed="handleExceed"
            :file-list="fileList">
            <el-button size="small" type="primary">点击上传</el-button>
            <div slot="tip" class="el-upload__tip">只能按照我们指定的模板上传</div>
          </el-upload>
        </el-col>
      </el-row>
    </el-tab-pane>
    <el-tab-pane label="单独录入" name="single">
        <el-row>
          <el-form ref="tempStudentInfo" :model="tempStudentInfo" label-width="140px">
            <el-form-item label="学号">
              <el-input v-model="tempStudentInfo.no"></el-input>
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
              <el-select v-model="tempStudentInfo.schoolNo" placeholder="请选择基层党组织">
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
                <el-button @click="insertSingleStudent()">提交</el-button>
              </el-col>
            </el-row>
            提示：权限编号 0-代表超级管理员、1-基层党组织管理员、2-党员、3-预备党员、4-入党积极分子、5-发展对象
          </el-form>
        </el-row>
    </el-tab-pane>
  </el-tabs>
</template>

<script>
  let config = require("../../config/config");
  const ip = config.ip;
    let $ = require("jquery");
    let moment = require("moment");
    export default {
        name: "insert-student",
        data(){
          return{
            parties:[],
            fileList:[],
            activeName:"templete",
            tempStudentInfo:{},
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
      methods: {
        handleRemove(file, fileList) {
          console.log(file, fileList);
        },
        handlePreview(file) {
          console.log(file);
        },
        handleExceed(files, fileList) {
          this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
        },
        beforeRemove(file, fileList) {
          return this.$confirm(`确定移除 ${ file.name }？`);
        },
        insertSingleStudent(){
          let self = this;
          this.tempStudentInfo.birth = moment(this.tempStudentInfo.birth).format("YYYY/MM/DD");
          this.tempStudentInfo.applyDate = moment(this.tempStudentInfo.applyDate).format("YYYY/MM/DD");
          this.tempStudentInfo.beDevelopDate = moment(this.tempStudentInfo.beDevelopDate).format("YYYY/MM/DD");
          this.tempStudentInfo.beActivistDate = moment(this.tempStudentInfo.beActivistDate).format("YYYY/MM/DD");
          console.log(this.tempStudentInfo)
          $.post(`http://${ip}/stu/addOne`,self.tempStudentInfo,function (response) {
            if(response.code == 1001){
              self.$message("添加学员成功");
              self.tempStudentInfo = {};
            }else{
              self.$message.error("添加学员失败");
            }
          })
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
      }
    }
</script>

<style scoped>
  .upload{
    margin-top: 20px;
  }
  .downloadExcel a{
    text-decoration: none;
    color:black;
  }
  .downloadExcel{
    margin-bottom: 20px;
  }
</style>
