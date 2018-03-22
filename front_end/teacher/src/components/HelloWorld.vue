<template>
  <div>
      尊敬的{{power}}，欢迎您
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
      power:"",
      msg: 'Welcome to Your Vue.js App'
    }
  },
  methods:{

  },
  beforeMount:function () {
    if(this.$store.power == 0){
      this.power = "超级管理员"
    }else{
      this.power = "基层管理员"
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
