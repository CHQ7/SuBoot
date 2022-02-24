<template>
  <div>
    <iframe :src="url" frameborder="0" height="800" scrolling="yes" width="100%" />
  </div>
</template>
<script>
export default {
  data() {
    return {
      url: this.domain + '/druid/login.html'
    }
  }
}
</script>

