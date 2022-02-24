<script>
export default {
  name: 'Redirect',
  created() {
    const { params, query } = this.$route
    const { path } = params
    this.$u.route.replace('/' + path, query)
  },
  render: function(h) {
    return h() // 避免警告信息
  }
}
</script>
