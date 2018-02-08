<template>
    <el-row type="flex">
        <el-col>
            <h5>菜单</h5>
            <el-menu default-active="/viewInfo" router>
                <el-menu-item index="/viewInfo">
                    <i class="el-icon-view"></i>
                    <span slot="title">查看信息</span>
                </el-menu-item>
                <el-menu-item index="/chooseClass">
                    <i class="el-icon-tickets"></i>
                    <span slot="title">选课</span>
                </el-menu-item>
                <el-menu-item index="/modifyInfo">
                    <i class="el-icon-plus"></i>
                    <span slot="title">查看已选课程信息</span>
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
