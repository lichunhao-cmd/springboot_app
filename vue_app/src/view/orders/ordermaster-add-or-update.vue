<template>
  <el-dialog
    :title="!dataForm.orderId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="买家名称" prop="buyerName">
      <el-input v-model="dataForm.buyerName" placeholder="买家名称"></el-input>
    </el-form-item>
    <el-form-item label="买家电话" prop="buyerPhone">
      <el-input v-model="dataForm.buyerPhone" placeholder="买家电话"></el-input>
    </el-form-item>
    <el-form-item label="座号" prop="buyerTableNumber">
      <el-input v-model="dataForm.buyerTableNumber" placeholder="座号"></el-input>
    </el-form-item>
    <el-form-item label="买家微信的openid" prop="buyerOpenid">
      <el-input v-model="dataForm.buyerOpenid" placeholder="买家微信的openid"></el-input>
    </el-form-item>
    <el-form-item label="订单总金额" prop="orderAmount">
      <el-input v-model="dataForm.orderAmount" placeholder="订单总金额"></el-input>
    </el-form-item>
    <el-form-item label="订单状态 , 默认0为新订单" prop="orderStatus">
      <el-input v-model="dataForm.orderStatus" placeholder="订单状态 , 默认0为新订单"></el-input>
    </el-form-item>
    <el-form-item label="支付状态 , 默认0为未支付" prop="payStatus">
      <el-input v-model="dataForm.payStatus" placeholder="支付状态 , 默认0为未支付"></el-input>
    </el-form-item>
    <el-form-item label="用户备注" prop="buyerRemarks">
      <el-input v-model="dataForm.buyerRemarks" placeholder="用户备注"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="修改时间" prop="updateTime">
      <el-input v-model="dataForm.updateTime" placeholder="修改时间"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          orderId: 0,
          buyerName: '',
          buyerPhone: '',
          buyerTableNumber: '',
          buyerOpenid: '',
          orderAmount: '',
          orderStatus: '',
          payStatus: '',
          buyerRemarks: '',
          createTime: '',
          updateTime: ''
        },
        dataRule: {
          buyerName: [
            { required: true, message: '买家名称不能为空', trigger: 'blur' }
          ],
          buyerPhone: [
            { required: true, message: '买家电话不能为空', trigger: 'blur' }
          ],
          buyerTableNumber: [
            { required: true, message: '座号不能为空', trigger: 'blur' }
          ],
          buyerOpenid: [
            { required: true, message: '买家微信的openid不能为空', trigger: 'blur' }
          ],
          orderAmount: [
            { required: true, message: '订单总金额不能为空', trigger: 'blur' }
          ],
          orderStatus: [
            { required: true, message: '订单状态 , 默认0为新订单不能为空', trigger: 'blur' }
          ],
          payStatus: [
            { required: true, message: '支付状态 , 默认0为未支付不能为空', trigger: 'blur' }
          ],
          buyerRemarks: [
            { required: true, message: '用户备注不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          updateTime: [
            { required: true, message: '修改时间不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.orderId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.orderId) {
            this.$http({
              url: this.$http.adornUrl(`/generator/ordermaster/info/${this.dataForm.orderId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.buyerName = data.orderMaster.buyerName
                this.dataForm.buyerPhone = data.orderMaster.buyerPhone
                this.dataForm.buyerTableNumber = data.orderMaster.buyerTableNumber
                this.dataForm.buyerOpenid = data.orderMaster.buyerOpenid
                this.dataForm.orderAmount = data.orderMaster.orderAmount
                this.dataForm.orderStatus = data.orderMaster.orderStatus
                this.dataForm.payStatus = data.orderMaster.payStatus
                this.dataForm.buyerRemarks = data.orderMaster.buyerRemarks
                this.dataForm.createTime = data.orderMaster.createTime
                this.dataForm.updateTime = data.orderMaster.updateTime
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/generator/ordermaster/${!this.dataForm.orderId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'orderId': this.dataForm.orderId || undefined,
                'buyerName': this.dataForm.buyerName,
                'buyerPhone': this.dataForm.buyerPhone,
                'buyerTableNumber': this.dataForm.buyerTableNumber,
                'buyerOpenid': this.dataForm.buyerOpenid,
                'orderAmount': this.dataForm.orderAmount,
                'orderStatus': this.dataForm.orderStatus,
                'payStatus': this.dataForm.payStatus,
                'buyerRemarks': this.dataForm.buyerRemarks,
                'createTime': this.dataForm.createTime,
                'updateTime': this.dataForm.updateTime
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
