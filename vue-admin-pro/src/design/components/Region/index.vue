<template>
  <el-cascader
    v-model="currentVal"
    :options="options"
    style="width: 100%"
    placeholder="请选择地区"
    :props="{value: 'label' }"
    clearable
    @change="handleChange"
  />
</template>

<script>
/**
 * 地区选择器
 */
import region from '../../data/region.json'
export default {
  name: 'Region',
  model: {
    prop: 'value',
    event: 'input'
  },
  props: {
    value: {
      type: Array,
      default() {
        return []
      }
    }
  },
  data() {
    return {
      options: region,
      currentVal: []
    }
  },
  watch: {
    value: {
      immediate: true,
      handler(val) {
        this.currentVal = val
      }
    },
    currentVal: {
      immediate: true,
      handler(val) {
        this.value = val
      }
    }
  },
  methods: {
    handleChange(value) {
      this.$emit('input', value)
      this.$emit('change', value)
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
