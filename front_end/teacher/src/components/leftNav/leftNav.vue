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
                      <el-menu-item index="/viewStudent">查看学员信息</el-menu-item>
                      <el-menu-item index="/insertStudent">录入学员信息</el-menu-item>
                      <el-menu-item index="/updateStudent">修改学员信息</el-menu-item>
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
