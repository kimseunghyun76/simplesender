export default{
  table: {
    tableClass: 'table b-table table-bordered table-hover ',
    ascendingIcon: 'fa fa-sort-up',
    descendingIcon: 'fa fa-sort-down',
    handleIcon: 'fa fa-bars',
    renderIcon: function (classes, options) {
      return `<span class="${classes.join(' ')}"></span>`
    }
  },
  pagination: {
    wrapperClass: 'pagination pull-right',
    activeClass: 'btn-primary',
    disabledClass: 'disabled',
    pageClass: 'btn btn-border',
    linkClass: 'btn btn-border',
    icons: {
      first: '',
      prev: '',
      next: '',
      last: ''
    }
  }
}
