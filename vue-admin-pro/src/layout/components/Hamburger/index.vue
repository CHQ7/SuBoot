<template>
  <div style="padding: 0 15px;" @click="toggleClick">
    <u-icon-svg name="menu1" class-name="hamburger" :class="{'is-active':isActive}" />
  </div>
</template>

<script>
/**
 * 汉堡菜单
 */
export default {
  name: 'Hamburger',
  props: {
    isActive: {
      type: Boolean,
      default: false
    }
  },
  methods: {
    toggleClick() {
      this.$emit('toggleClick')
    }
  }
}
</script>

<style lang="scss"  scoped>
$color:#ffffff;
.hamburger {
  display: inline-block;
  vertical-align: middle !important;
  fill: $color !important;
}

.hamburger.is-active {
  transform: rotate(180deg);
}
</style>
