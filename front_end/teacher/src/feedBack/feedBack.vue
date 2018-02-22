<template>
  <div>



    <!--修改到课情况-->
    <el-dialog  :visible.sync="feedBackFlag" title ="修改到课情况">
      <el-row>
        <el-col :span="18" :offset="5">
          <el-transfer v-model="absentStudent" :data="student"     :titles="['到课学生', '未到课学生']" filterable></el-transfer>
        </el-col>
      </el-row>
      <span>学生总人数{{studentLength}}</span>
      <span>/缺席人数{{absentStudent.length}}</span>
    </el-dialog>
  </div>
</template>

<script>
    import ElSpinner from "element-ui/packages/spinner/src/spinner";
    let ip = require("../../config/config").ip;
    let $ = require("jquery");

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
              feedBackFlag:true
          }
        },
      computed:{
          studentLength:function () {
            return this.student.length;
          }
      },
      beforeMount:function(){
        let self = this;
        let result;
        $.post(`http://${ip}/course/allInfo`,function(response){
          if(response.code == 1001){
            result = response.data;
          }else{
            result = false;
          }
        });
        console.log(result);
      }
    }
</script>
<style scoped>

</style>
