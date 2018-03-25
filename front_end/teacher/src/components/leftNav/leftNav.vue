<template>
    <el-row type="flex">
        <el-col>
            <h5>菜单</h5>
            <el-menu router>
                  <el-submenu index="1">
                    <template slot="title">
                      <i class="el-icon-info"></i>
                      <span>学员管理</span>
                    </template>
                    <el-menu-item-group>
                      <el-menu-item index="/viewStudent">管理学员信息</el-menu-item>
                      <el-menu-item index="/insertStudent">录入学员信息</el-menu-item>
                    </el-menu-item-group>
                  </el-submenu>
                <el-submenu index="2">
                  <template slot="title">
                    <i class="el-icon-date"></i>
                    <span>课程管理</span>
                  </template>
                  <el-menu-item-group>
                    <el-menu-item index="/releaseClass">发布课程信息</el-menu-item>
                    <el-menu-item index="/updateClass">修改课程信息</el-menu-item>
                    <el-menu-item index="/selectSeats">查询选座状况</el-menu-item>
                    <el-menu-item index="/feedBack">反馈到课情况</el-menu-item>
                  </el-menu-item-group>
                </el-submenu>
              <el-menu-item index="/changePassword">
                <i class="el-icon-warning"></i>
                <span slot="title">修改密码</span>
              </el-menu-item>
              <el-menu-item index="/admin">
                <i class="el-icon-menu"></i>
                <span slot="title">管理员</span>
              </el-menu-item>
              <el-menu-item  index="/feedBackMessage">
                <i class="el-icon-document"></i>
                <span slot="title">反馈信息列表</span>
              </el-menu-item>
            </el-menu>
        </el-col>
    </el-row>
</template>
<style>

</style>
<script>
    export default {
        name:'leftNav',
        method:{

        },
        data(){
            return {

            }
        }
    }
</script>
