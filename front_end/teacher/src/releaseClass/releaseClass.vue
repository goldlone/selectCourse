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
            <el-input v-model="tempClass.stage"></el-input>
          </el-form-item>
          <el-form-item label="教室">
            <el-input v-model="tempClass.classroom"></el-input>
          </el-form-item>
          <el-form-item label="教师">
            <el-input v-model="tempClass.teacher"></el-input>
          </el-form-item>
          <el-form-item label="选课时间">
            <el-date-picker
              v-model="tempClass.selectClassTime"
              type="datetimerange"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="开课时间">
            <el-date-picker
              v-model="tempClass.classTime"
              type="datetimerange"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="所占学时">
            <el-input v-model="tempClass.time"></el-input>
          </el-form-item>
          <el-form-item label="上课学生权限">
            <el-select v-model="tempClass.power">
              <el-option v-for="item in powerOption" :key="item.value" :label="item.label" :value="item.value">

              </el-option>
            </el-select>
          </el-form-item>
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
    export default {
        name: "release-class",
        data(){
          return {
            tempClass:{},//用来存储新建的课程信息,
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
        methods:{
          releaseNewClass(){
              console.log(this.tempClass)
          }
        }
    }
</script>

<style scoped>

</style>
