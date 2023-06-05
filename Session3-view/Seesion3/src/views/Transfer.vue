<template>
    <div>
      <h1 align="center">Transfer Asset</h1>
      <el-form ref="form" :model="form" label-width="250px" style="width: 1200px;margin: 0 auto;">
        <div class="group">
          <h2>Selected Asset</h2>
          <div class="content-box">
            <el-form-item label="Asset SN :">
              <el-input v-model="form.assetSN" class="input-width" :disabled="true"></el-input>
            </el-form-item>
            <el-form-item label="Asset Name :">
              <el-input v-model="form.name" class="input-width" placeholder="Asset Name" :disabled="true"></el-input>
            </el-form-item>
          </div>
          <div class="content-box">
            <el-form-item label="Current Department :">
              <el-input v-model="form.deptment" class="input-width" placeholder="Current Department" :disabled="true"></el-input>
            </el-form-item>
            <el-form-item label="Current Department Location :">
              <el-input v-model="form.location" class="input-width" placeholder="Current Department Location" :disabled="true"></el-input>
            </el-form-item>
          </div>
        </div>
        <div class="group">
          <h2>Destination Department</h2>
          <div class="content-box">
            <el-form-item label="Destination Department :">
              <el-select v-model="form.department" placeholder="Select Department" class="input-width">
                <el-option v-for="item in departmentList" :value="item" :key="item"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="Destination Department Location :">
              <el-select v-model="form.newLocation" placeholder="Select Location" class="input-width">
                <el-option v-for="item in locationList" :value="item" :key="item"></el-option>
              </el-select>
            </el-form-item>
          </div>
          <div>
            <el-form-item label="New Asset SN :">
              <el-input v-model="form.name" :disabled="true"></el-input>
            </el-form-item>
          </div>
        </div>
        <el-form-item style="margin-top: 50px;text-align: center;margin-left: -250px">
          <el-button type="primary" @click="TransferAsset">Submit</el-button>
          <el-button v-on:click="close" style="margin-left: 200px">Close</el-button>
        </el-form-item>
      </el-form>
    </div>
</template>

<script>
import {DeportmentList, getLocationList} from '../api/main'
import {TransferAsset} from '../api/TransferApi'

export default {
  name: 'Transfer',
  data () {
    return {
      form: {
        name: '',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: '',
        department: '',
        location: '',
        newLocation: '',
        assetSN: ''
      },
      departmentList: [],
      locationList: []
    }
  },
  mounted: function () {
    this.assignment()
    this.getDepartmentList()
    this.getLocationList()
  },
  methods: {
    getLocationList () {
      getLocationList().then((res) => {
        if (res.data.code === 200) {
          this.locationList = res.data.data
        } else {
          this.$message.error(res.data.data.message)
        }
      })
    },
    getDepartmentList () {
      DeportmentList().then((res) => {
        if (res.data.code === 200) {
          this.departmentList = res.data.data
        } else {
          this.$message.error(res.data.data.message)
        }
      })
    },
    TransferAsset () {
      let item = this.$route.query.item
      TransferAsset({
        departmentName: this.form.department,
        newLocationName: this.form.location,
        assetId: this.asserId,
        assSN: item.assetSN,
        groupId: item.assetGroupName,
        warranty: item.warranty,
        fromDeptLocationID: item.deptLocationId
      }).then((res) => {
        if (res.data.code === 200) {
          this.$message.success(res.data.message)
          this.$router.push('/main')
        } else {
          this.$message.error(res.data.message)
        }
      })
      console.log(this.$data)
    },
    assignment () {
      let item = this.$route.query.item
      if (item != null) {
        this.form.name = item.assetname
        this.form.assetSN = item.assetSN
        this.form.group = item.assetGroupName
        this.form.deptment = item.deptName
        this.form.description = item.description
        this.form.date1 = item.warranty
        this.form.location = item.locName
        this.asserId = item.assId
        console.log(item)
        this.$message.info('Please select the destination for modifying the asset!')
      }
    },
    close () {
      if (confirm('Are you sure you want to cancel?')) {
        this.$router.push('/main')
      }
    }
  }
}
</script>

<style scoped>
    .content-box{
      display: flex;
      justify-content: space-between;
      align-content: center;
    }
    .input-width{
      width: 300px;
    }
</style>
