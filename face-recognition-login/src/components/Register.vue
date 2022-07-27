<template>
  <div class="app">


    <el-input prefix-icon="el-icon-user" clearable v-model="stu.stuName" placeholder="学生姓名">
    </el-input>


    <el-button circle id="camera" @click="camera" type="success"><i class="el-icon-camera"></i></el-button>

    <div class="showVideo">
      <video ref="video" autoplay></video>
    </div>

    <div class="showImg">
      <canvas v-show="takePhoto" ref="canvas"></canvas>
    </div>


    <el-button type="primary" @click="photograph">拍照</el-button>
    <el-button @click="onRegister">录入</el-button>

  </div>
</template>

<script>
import {Loading} from 'element-ui';
import api from "@/api/api";

export default {
  name: "Register",
  data() {
    return {
      cameraOpen: false,
      takePhoto: false,
      stu: {
        stuName: "",
        stuPhoto: null
      }
    }
  },
  methods: {
    async onRegister() {
      if (this.stu.stuPhoto === "" || this.stu.stuName === "") {
        this.$message.warning("请填写姓名或者拍摄人脸照片")
      } else {
        let [data, error] = await api.register(this.stu)
        if (data) {
          if (data.code === 0) {
            this.$message.success("登录成功")
            this.$router.push("/index")
          } else {
            this.$message.warning(data.msg)
          }
        } else {
          this.$message.error("录入失败")
          console.log(error)
        }
      }
    },
    // 调用摄像头
    camera() {
      let loading = Loading.service({fullscreen: true});
      if (this.cameraOpen) {
        if (!this.$refs['video'].srcObject) {
          loading.close();
          return
        }
        let stream = this.$refs['video'].srcObject
        let tracks = stream.getTracks()
        tracks.forEach(track => {
          track.stop()
        })
        this.$refs['video'].srcObject = null
        loading.close();
      } else {
        // H5调用电脑摄像头API
        navigator.mediaDevices.getUserMedia({
          video: true
        }).then(success => {
          loading.close();
          // 摄像头开启成功
          this.$refs['video'].srcObject = success
          // 实时拍照效果
          this.$refs['video'].play()
        }).catch(error => {
          loading.close();
          this.$message.warning('摄像头开启失败，请检查摄像头是否可用！')
        })
      }
      this.cameraOpen = !this.cameraOpen
    },
    // 拍照
    photograph() {
      let ctx = this.$refs['canvas'].getContext('2d')
      // 把当前视频帧内容渲染到canvas上
      ctx.drawImage(this.$refs['video'], 0, 0, 240, 170)
      // 转base64格式、图片格式转换、图片质量压缩
      let imgBase64 = this.$refs['canvas'].toDataURL('image/jpeg', 1.0)
      this.stu.stuPhoto = this.base64ToBlob(imgBase64);
      // 图片尺寸  用于判断
      this.takePhoto = true
    },
    base64ToBlob(urlData) {
      var arr = urlData.split(',');
      var mime = arr[0].match(/:(.*?);/)[1] || 'image/png';
      // 去掉url的头，并转化为byte
      var bytes = window.atob(arr[1]);
      // 处理异常,将ascii码小于0的转换为大于0
      var ab = new ArrayBuffer(bytes.length);
      // 生成视图（直接针对内存）：8位无符号整数，长度1个字节
      var ia = new Uint8Array(ab);

      for (var i = 0; i < bytes.length; i++) {
        ia[i] = bytes.charCodeAt(i);
      }

      return new Blob([ab], {
        type: mime
      });
    }
  }

}
</script>

<style scoped lang="less">

.app {
  width: 100%;
  text-align: center;
  align-content: center;

  #camera {
    margin-top: 20px;
  }

  .showImg {
    margin: 20px;
    width: 100%;

    canvas {
      width: 300px;
    }
  }

  .showVideo {
    width: 100%;
    height: 300px;
    margin: 20px;

    video {
      width: 80%;
      height: 300px;
    }
  }


}


</style>