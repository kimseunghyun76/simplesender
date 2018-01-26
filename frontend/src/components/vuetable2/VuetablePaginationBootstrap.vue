<template>
  <div>
  <ul class="pagination b-paginatoin pagination-md justify-content-end">
    <li :class="{'disabled': isOnFirstPage}" class="page-item">
      <a href="" @click.prevent="loadPage('prev')" class="page-link">
        <span>&laquo;</span>
      </a>
    </li>
    <template v-if="notEnoughPages">
      <li v-for="n in totalPage" :class="{'active': isCurrentPage(n)}" class="page-item">
        <a @click.prevent="loadPage(n)" v-html="n"  class="page-link"></a>
      </li>
    </template>
    <template v-else>
      <li v-for="n in windowSize" :class="{'active': isCurrentPage(windowStart+n-1)}" class="page-item">
        <a @click.prevent="loadPage(windowStart+n-1)" v-html="windowStart+n-1"  class="page-link"></a>
      </li>
    </template>
    <li :class="{'disabled': isOnLastPage}"  class="page-item">
      <a href="" @click.prevent="loadPage('next')" class="page-link">
        <span>&raquo;</span>
      </a>
    </li>
  </ul>
  
    <!--b-pagination size="md" align="right" :total-rows="100" v-model="currentPage" :per-page="10">
    </b-pagination-->
  </div>
</template>

<script>
import VuetablePaginationMixin from 'vuetable-2/src/components/VuetablePaginationMixin'
export default {
  mixins: [VuetablePaginationMixin]
}
</script>
