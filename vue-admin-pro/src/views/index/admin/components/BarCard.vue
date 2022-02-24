<template>
  <div class="card">
    <div class="card-head">
      <div class="card-head-title">{{ describe.title }}</div>
      <div v-if="describe.more" class="card-head_more">
        <router-link v-if="!describe.target" :to="describe.url">
          {{ describe.more }}<i class="el-icon-arrow-right" />
        </router-link>
        <a v-if="describe.target && describe.url" :href="describe.url" target="_blank">
          {{ describe.more }}<i class="el-icon-arrow-right" />
        </a>
      </div>
    </div>
    <div class="card-content">
      <slot name="content" />
    </div>
  </div>
</template>

<script>
export default {
  name: 'BarCard',
  props: {
    describe: {
      type: Object,
      default() {
        return { }
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.card{
  width: 100%;
  border-radius: 5px;
  background-color: #FFFF;
  font-family: "微软雅黑";
  margin-bottom: 20px;
  .card-head{
    padding: 20px 40px 0;
    font-size: 14px;
    font-weight: bold;
    color: rgba(85,85,85,1);
    overflow: hidden;
    .card-head-title{
      float: left;
      line-height: 1;
    }
    .card-head_more{
      float: right;
      line-height: 1;
      .el-icon-arrow-right{
        font-weight: 900;
      }
    }
  }
  .card-content{
    width: 100%;
    padding:20px 40px;
    overflow: hidden;
  }
}
</style>
