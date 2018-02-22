<template>
  <div>
    <h1>发布课程</h1>
    <el-row>
      <el-col :span="18" :offset="3">
        <el-form label-width="80px">
          <el-form-item label="活动名称">
            <el-input></el-input>
          </el-form-item>
        </el-form>
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

          }
        },
        methods:{

        }
    }
</script>

<style scoped>

</style>
