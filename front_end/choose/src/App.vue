<template>
  <div id="app">
    <el-container>
      <el-header>
        <el-menu class="el-menu-demo" mode="horizontal">
          <el-menu-item index="1">学生端</el-menu-item>
        </el-menu>
      </el-header>
      <el-container>
        <el-aside width="200px">
          <left-nav></left-nav>
        </el-aside>
        <el-main>
          <router-view/>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
  import requests from './logic/studentLogic'
  import LeftNav from './components/leftNav/leftNav'
  export default {
    name: 'app',
    components:{LeftNav},
    data () {
      return {

      }
    },
    methods:{

    },
    computed:{

    }
  }
</script>

<style>
</style>
