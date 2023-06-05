<template>
  <div>
    <h1 class="header" style="text-align: center;">Asset Management</h1>
    <el-form :inline="true" :model="fromSelect" class="form-select" style="text-align: center;">
      <el-form-item label="Asset SN:">
        <el-input v-model="fromSelect.Asset"></el-input>
      </el-form-item>
      <el-form-item label="Deportment:">
        <el-select v-model="fromSelect.department" placeholder="Select Department">
          <el-option v-for="item in deptData" :key="item" :value="item"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="Asset Group:">
        <el-select v-model="fromSelect.group" placeholder="Select Group">
          <el-option v-for="item in groupData" :key="item" :value="item"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="Search">Search</el-button>
      </el-form-item>
    </el-form>
    <div style="width: 100%;">
      <el-table :data="tableData" @selection-change="userInformation" border height="445" class="table-content" strip >
        <el-table-column align="center" prop="AssetSN" label="Asset SN" width="155"></el-table-column>
        <el-table-column align="center" prop="AssetName" label="Asset Name" width="155"></el-table-column>
        <el-table-column align="center" prop="deptname" label="Department" width="155"></el-table-column>
        <el-table-column align="center" prop="assetgname" label="Asset Group" width="155"></el-table-column>
        <el-table-column align="center" prop="Description" label="Description" width="155"></el-table-column>
        <el-table-column align="center" prop="warrantyDate" label="WarrantyDate" width="155"></el-table-column>
        <el-table-column align="center" label="Edit" width="155">
          <template slot-scope="scope">
            <el-button v-on:click="register(scope.row)" type="text" size="small">Edit</el-button>
          </template>
        </el-table-column>
        <el-table-column align="center" label="Transfer" width="155">
          <template slot-scope="scope">
            <el-button v-on:click="transfer(scope.row)" type="text" size="small">Transfer</el-button>
          </template>
        </el-table-column>
        <el-table-column align="center" label="Transfer History" width="155">
          <el-button v-on:click="TransferHistory" type="text" size="small">Transfer History</el-button>
        </el-table-column>
      </el-table>
    </div>
    <div style="width: 1420px;margin-top: 30px">
      <el-button type="success" style="float: right" v-on:click="Register">Register</el-button>
    </div>
    <router-view/>
  </div>
</template>

<script>
import {AssetGroupList, AssetList, DeportmentList, getAssetList} from '../api/main'

export default {
  name: 'Main',
  data () {
    return {
      fromSelect: {
        user: '',
        region: '',
        Asset: '',
        department: '',
        group: ''
      },
      tableData: [{
        AssetSN: '',
        AssetName: '',
        deptname: '',
        assetgname: '',
        Description: '',
        warrantyDate: '',
        locationName: '',
        assetId: ''
      }],
      multipleSelection: [],
      ids: [],
      deptData: [],
      groupData: []
    }
  },
  mounted: function () {
    this.getAssetList()
    this.getDeportment()
    this.getAssetGroup()
  },
  methods: {
    getDeportment () {
      DeportmentList().then((res) => {
        if (res.data.code === 200) {
          this.deptData = res.data.data
        } else {
          this.$message.error(res.data.message)
        }
      })
    },
    getAssetGroup () {
      AssetGroupList().then((res) => {
        if (res.data.code === 200) {
          this.groupData = res.data.data
        } else {
          this.$message.error(res.data.message)
        }
      })
    },
    Search () {
      getAssetList({
        assetSN: this.fromSelect.Asset,
        deptName: this.fromSelect.department,
        assetGroup: this.fromSelect.group
      }).then((res) => {
        if (res.data.code === 200) {
          this.tableData = res.data.data
        } else {
          this.$message.error(res.data.message)
        }
      })
    },
    Register () {
      this.$router.push('/main/register')
    },
    register (item) {
      this.$router.push({
        name: 'register',
        query: {
          item: {
            assetSN: item.AssetSN,
            assetname: item.AssetName,
            deptName: item.deptname,
            assetGroupName: item.assetgname,
            description: item.Description,
            warranty: item.warrantyDate,
            locName: item.locationName,
            assId: item.assetId,
            deptLocationId: item.DepartmentLocationID
          }
        }
      })
    },
    transfer (item) {
      this.$router.push({
        name: 'transfer',
        query: {
          item: {
            assetSN: item.AssetSN,
            assetname: item.AssetName,
            deptName: item.deptname,
            assetGroupName: item.assetgname,
            description: item.Description,
            warranty: item.warrantyDate,
            locName: item.locationName,
            assId: item.assetId,
            deptLocationId: item.DepartmentLocationID
          }
        }
      })
    },
    TransferHistory () {
      this.$router.push('/main/history')
    },
    created () {
      this.getAssetList()
      this.getDeportment()
    },
    userInformation (val) {
      this.multipleSelection = val
      this.multipleSelection.forEach((item) => {
        this.ids.push(item.id)
      })
    },
    getAssetList () {
      AssetList().then((res) => {
        if (res.data.code === 200) {
          this.tableData = res.data.data
        } else {
          this.$message.error(res.data.message)
        }
      })
        .catch((err) => {
          console.log(err)
        })
    }
  }
}
</script>

<style scoped>
  .table-content{
    width: 1396px;
    text-align: center;
    margin: 0 auto;
  }
</style>
