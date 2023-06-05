<template>
  <div>
    <h1 style="text-align: center">Transfer History</h1>
    <el-table :data="tableData" height="600" border style="width: 1316px;margin: 0 auto;">
      <el-table-column align="center" prop="transferDate" label="TransferDate" width="180"></el-table-column>
      <el-table-column align="center" prop="oldDepartment" label="OldDepartment" width="180"></el-table-column>
      <el-table-column align="center" prop="oldDepartmentLocation" label="OldDepartmentLocation" width="200"></el-table-column>
      <el-table-column align="center" prop="oldAssetSN" label="OlsAssetSN" width="180"></el-table-column>
      <el-table-column align="center" prop="newDepartment" label="NewDepartment" width="180"></el-table-column>
      <el-table-column align="center" prop="newDepartmentLocation" label="NewDepartmentLocation" width="200"></el-table-column>
      <el-table-column align="center" prop="newAssetSN" label="NewAssetNS" width="180"></el-table-column>
    </el-table>
    <div style="width: 1276px;margin: 0 auto;">
      <el-button type="warning" style="float: right;margin-top: 10px" v-on:click="close">Close</el-button>
    </div>
    <router-view/>
  </div>
</template>

<script>
import {getHistoryList} from '../api/HistoryApi'

export default {
  name: 'History',
  data () {
    return {
      tableData: [{
        newDepartment: '',
        newDepartmentLocation: '',
        oldAssetSN: '',
        oldDepartmentLocation: '',
        newAssetSN: '',
        transferDate: '',
        oldDepartment: ''
      }]
    }
  },
  mounted: function () {
    this.HistoryList()
  },
  methods: {
    HistoryList () {
      getHistoryList().then(res => {
        if (res.data.code === 200) {
          this.$message.success(res.data.message)
          this.tableData = res.data.data
        } else {
          this.$message.error(res.data.message)
        }
      })
    },
    close () {
      if (confirm('Are you sure you want to exit?')) {
        this.$router.push('/main')
      }
    }
  }
}
</script>

<style scoped>

</style>
