<template>
  <el-dialog
    :title="!dataForm.goodsId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="商品名称" prop="goodsName">
      <el-input v-model="dataForm.goodsName" placeholder="商品名称"></el-input>
    </el-form-item>
    <el-form-item label="商品单价" prop="goodsPrice">
      <el-input v-model="dataForm.goodsPrice" placeholder="商品单价"></el-input>
    </el-form-item>
<!--    <el-form-item label="库存" prop="goodsStock">-->
<!--      <el-input v-model="dataForm.goodsStock" placeholder="库存"></el-input>-->
<!--    </el-form-item>-->

    <el-form-item label="商品图片" prop="goodsIcon">
      <el-upload
        class="avatar-uploader"
        action="/api/upload"
        :show-file-list="false"
        :on-success="handleAvatarSuccess"
        :before-upload="beforeAvatarUpload">
        <img v-if="imageUrl" :src="imageUrl" class="avatar">
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
      </el-upload>
    </el-form-item>
    <el-form-item label="商品描述" prop="goodsDescription">
      <el-input v-model="dataForm.goodsDescription" placeholder="商品描述"></el-input>
    </el-form-item>
<!--    <el-form-item label="商品状态 , 0正常 , 1下架" prop="goodsStatus">-->
<!--      <el-input v-model="dataForm.goodsStatus" placeholder="商品状态 , 0正常 , 1下架"></el-input>-->
<!--    </el-form-item>-->
    <el-form-item label="类目" prop="categoryType" >
        <el-select  placeholder="请选择活动区域" v-model="dataForm.categoryType">
          <el-option v-for="item in category" :label="item.categoryName" :value="item.categoryId" ></el-option>
        </el-select>
    </el-form-item>
<!--    <el-form-item label="创建时间" prop="createTime">-->
<!--      <el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>-->
<!--    </el-form-item>-->
<!--    <el-form-item label="修改时间" prop="updateTime">-->
<!--      <el-input v-model="dataForm.updateTime" placeholder="修改时间"></el-input>-->
<!--    </el-form-item>-->
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
        imageUrl: '',
        category:[],
        dataForm: {
          goodsId: 0,
          goodsName: '',
          goodsPrice: '',
          goodsIcon: '',
          goodsDescription: '',
          categoryType: '',
          createTime: '',
          updateTime: ''
        },
        dataRule: {
          goodsName: [
            { required: true, message: '商品名称不能为空', trigger: 'blur' }
          ],
          goodsPrice: [
            { required: true, message: '商品单价不能为空', trigger: 'blur' }
          ],
          goodsStock: [
            { required: true, message: '库存不能为空', trigger: 'blur' }
          ],
          goodsIcon: [
            { required: true, message: '商品图片不能为空', trigger: 'blur' }
          ],
          goodsDescription: [
            { required: true, message: '商品描述不能为空', trigger: 'blur' }
          ],
          goodsStatus: [
            { required: true, message: '商品状态 , 0正常 , 1下架不能为空', trigger: 'blur' }
          ],
          categoryType: [
            { required: true, message: '类目编号不能为空', trigger: 'blur' }
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
      handleAvatarSuccess(res, file) {
        this.dataForm.goodsIcon = res.data
        this.imageUrl = URL.createObjectURL(file.raw);
      },
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
      },


      init (id) {

        this.$axios.get('/api/goods/category').then((res=>{
          this.category = res.data.data;
        }))

        this.dataForm.goodsId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.goodsId) {
            this.$axios.get('/api/goods/info?'+"goodsId="+this.dataForm.goodsId,{
            }).then((res=>{
              this.dataForm.goodsName = res.data.data.goodsName
              this.dataForm.goodsPrice = res.data.data.goodsPrice
              this.dataForm.goodsIcon = res.data.data.goodsIcon
              this.dataForm.goodsDescription = res.data.data.goodsDescription
              this.dataForm.categoryType = res.data.data.categoryType
              this.imageUrl = '/api/goodsImg'+res.data.data.goodsIcon
            }))

          }
        })
      },
      // 表单提交
      dataFormSubmit () {

        this.$refs['dataForm'].validate((valid)=>{
          if (valid){
            if (this.dataForm.goodsId == 0){
              this.$axios.post('/api/goods/insert',{
                goodsId: this.dataForm.goodsId || undefined,
                goodsName: this.dataForm.goodsName,
                goodsPrice: this.dataForm.goodsPrice,
                goodsIcon: this.dataForm.goodsIcon,
                goodsDescription: this.dataForm.goodsDescription,
                categoryType: this.dataForm.categoryType,
              }).then((rest)=>{
                alert("success")
                this.visible = false
                this.$emit('refreshDataList')
              })
            }else {
              this.$axios.post('/api/goods/update',{
                goodsId: this.dataForm.goodsId || undefined,
                goodsName: this.dataForm.goodsName,
                goodsPrice: this.dataForm.goodsPrice,
                goodsIcon: this.dataForm.goodsIcon,
                goodsDescription: this.dataForm.goodsDescription,
                categoryType: this.dataForm.categoryType,
              }).then((rest)=>{
                alert("success")
                this.visible = false
                this.$emit('refreshDataList')
              })
            }

          }
        })

      }
    },
  }
</script>
<style>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
