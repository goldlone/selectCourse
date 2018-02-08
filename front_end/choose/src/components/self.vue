<template>
  <div>
    欢迎登录党委选课系统，请按照指示进行您的操作
  </div>
</template>

<script>
  import studentOperation from "../logic/studentLogic";
  import classOperation from "../logic/classLogic";
  export default {
    name: 'app',
    data () {
      return {
        choosedFunction:"self",
        functions:["class","self","update"],
        routers:["chooseClass","self","updateSelf"]
      }
    },
    methods:{

    },
    created:function () {
        let data;
        (async function(){
          try{
            let studentState = await studentOperation.getAllInfo();
            console.log(studentState)
            this.$store.state.studentState = studentState;
          }catch (e){
            console.log(e)
          }
        })()
      }
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
