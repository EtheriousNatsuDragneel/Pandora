<template>
  <div class="app-container">
    <el-form :model="queryParam" ref="queryForm" :inline="true">
      <el-form-item label="用户名：">
        <el-input v-model="queryParam.userName"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm">查询</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="listLoading" :data="tableData" border fit highlight-current-row style="width: 100%">
      <el-table-column prop="id" label="Id" />
      <el-table-column prop="user.userName" label="用户名"/>
      <el-table-column prop="user.realName" label="真实姓名" />
      <el-table-column prop="user.sex" label="性别" width="60px;" :formatter="sexFormatter"/>
      <el-table-column prop="paperName" label="试卷名称"/>
      <el-table-column prop="userScore" label="分数"/>
      <el-table-column prop="paperType" label="试卷类型" :formatter="paperTypeEnumFormatter"/>
      <el-table-column prop="subjectName" label="试卷类别"/>
      <el-table-column prop="createTime" label="考试时间" width="160px"/>

      <el-table-column width="220px" label="操作" align="center">
        <template slot-scope="{row}">
          <router-link :to="{path:'/user/teacher/edit', query:{id:row.id}}" v-if="row.status === 2" class="link-left">
            <el-button size="mini" type="success" >查看</el-button>
          </router-link>
          <router-link :to="{path:'/user/teacher/read',query:{id:row.id}}" v-if="row.status === 1 ">
            <el-button type="warning" size="mini" >批改</el-button>
          </router-link>
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="queryParam.pageIndex" :limit.sync="queryParam.pageSize"
                @pagination="search"/>
  </div>
</template>

<script>
import { mapGetters, mapState } from 'vuex'
import Pagination from '@/components/Pagination'
import examPaperApi from '@/api/examPaper'

export default {
  components: { Pagination },
  data () {
    return {
      queryParam: {
        userName: '',
        pageIndex: 1,
        pageSize: 10
      },
      listLoading: true,
      tableData: [],
      total: 0
    }
  },
  created () {
    this.search()
  },
  methods: {
    search () {
      this.listLoading = true
      examPaperApi.pagePaperList(this.queryParam).then(data => {
        const re = data.response
        this.tableData = re.list
        console.log(re.list)
        this.total = re.total
        this.queryParam.pageIndex = re.pageNum
        this.listLoading = false
      })
    },
    submitForm () {
      this.queryParam.pageIndex = 1
      this.search()
    },
    sexFormatter  (row, column, cellValue, index) {
      return this.enumFormat(this.sexEnum, cellValue)
    },
    statusFormatter (status) {
      return this.enumFormat(this.statusEnum, status)
    },
    statusTagFormatter (status) {
      return this.enumFormat(this.statusTag, status)
    },
    statusBtnFormatter (status) {
      return this.enumFormat(this.statusBtn, status)
    },
    paperTypeEnumFormatter (row, column, cellValue, index) {
      return this.enumFormat(this.paperTypeEnum, cellValue)
    }
  },
  computed: {
    ...mapGetters('enumItem', [
      'enumFormat'
    ]),
    ...mapState('enumItem', {
      sexEnum: state => state.user.sexEnum,
      statusEnum: state => state.user.statusEnum,
      statusTag: state => state.user.statusTag,
      statusBtn: state => state.user.statusBtn,
      paperTypeEnum: state => state.exam.examPaper.paperTypeEnum
    })
  }
}
</script>
