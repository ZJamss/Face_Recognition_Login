<template>
  <div class="app">
    <el-card>
      <el-header>
        <el-menu router mode="horizontal">
          <el-menu-item index="1" route="/login">
            登录
          </el-menu-item>
          <el-menu-item index="2" route="/register">
            录入
          </el-menu-item>
        </el-menu>
      </el-header>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-card>
  </div>
</template>

<script>

export default {
  name: "LoginPage",
  data() {
    return {};
  },
  methods: {
  },
  mounted() {
  }
}
</script>

<style scoped>
.app {
  width: 40%;
  margin: 4% auto;
}
</style>