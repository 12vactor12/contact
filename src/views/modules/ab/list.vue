<template>
  <div class="main-content">
    <!-- 列表页 -->
    <div v-if="showFlag">
      <el-form :inline="true" :model="searchForm" class="form-content">
        <el-row :gutter="20" class="slt" :style="{justifyContent:contents.searchBoxPosition=='1'?'flex-start':contents.searchBoxPosition=='2'?'center':'flex-end'}">
                <el-form-item :label="'姓名'">
                  <el-input v-if="contents.inputIcon == 0" v-model="searchForm.name" clearable></el-input>
                </el-form-item>
                                                                              <el-form-item :label="'手机号'">
                  <el-input v-if="contents.inputIcon == 0" v-model="searchForm.phone" clearable></el-input>
                </el-form-item>
				</el-form-item>
				                                                              <el-form-item :label="'收藏状态'">
				  <el-select v-model="searchForm.collect" placeholder="请选择">
					  <el-option label="未收藏" value="未收藏"></el-option>
					  <el-option label="已收藏" value="已收藏"></el-option>
				  </el-select>
				</el-form-item>
			
                                                                                                                                                                                                               <el-form-item>
            <el-button type="success" @click="search()">查询</el-button>
          </el-form-item>
        </el-row>
        <el-row class="ad" :style="{justifyContent:contents.btnAdAllBoxPosition=='1'?'flex-start':contents.btnAdAllBoxPosition=='2'?'center':'flex-end'}">
          <el-form-item>
            <el-button
              type="success"
              icon="el-icon-plus"
              @click="addOrUpdateHandler()"
            >新增</el-button>
            <el-button
             
              type="danger"
              icon="el-icon-delete"
              @click="deleteHandler()"
            >删除</el-button>
			
			  <el-button
			    type="success"
			  ><download-excel
			  :data="dataList"
			  :fields="json_fields"
			  name="filename.xls">导出&nbsp;<i class="el-icon-minus"></i></download-excel></el-button>
			
			<el-upload action="http://127.0.0.1:8082/address/ei/import" :show-file-list="false" accept="xlsx" :on-success="handleExcelImportSuccess" style="display: inline-block;">
			<el-button type="primary">导入&nbsp;<i class="el-icon-plus"></i></el-button>
		  </el-upload>
            </el-form-item>
			
        </el-row>
      </el-form>
      <div class="table-content">
        <el-table class="tables" :size="contents.tableSize" :show-header="contents.tableShowHeader"
            :header-row-style="headerRowStyle" :header-cell-style="headerCellStyle"
            :border="contents.tableBorder"
            :fit="contents.tableFit"
            :stripe="contents.tableStripe"
            :row-style="rowStyle"
            :cell-style="cellStyle"
            :style="{width: '100%',fontSize:contents.tableContentFontSize,color:contents.tableContentFontColor}"
            :data="dataList"
            v-loading="dataListLoading"
            @selection-change="selectionChangeHandler">
            <el-table-column  v-if="contents.tableSelection"
                type="selection"
                header-align="center"
                align="center"
                width="50">
            </el-table-column>
            <el-table-column label="索引" v-if="contents.tableIndex" type="index" width="50" />
            	                                    <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign"
                    prop="id"
                    header-align="center"
		    label="姓名">
		     <template slot-scope="scope">
                       {{scope.row.name}}
                     </template>
                </el-table-column>
                <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign"
                    prop="phone"
                    header-align="center"
		    label="手机号">
		     <template slot-scope="scope">
		               {{scope.row.phone}}
		             </template>
		        </el-table-column>
			<el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign"
					prop="email"
					header-align="center"
			label="电子邮件地址">
			 <template slot-scope="scope">
					   {{scope.row.email}}
					 </template>
				</el-table-column>
				<el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign"
						prop="smaccounts"
						header-align="center"
				label="社交媒体账号">
				 <template slot-scope="scope">
						   {{scope.row.smaccounts}}
						 </template>
					</el-table-column>
					<el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign"
							prop="address"
							header-align="center"
					label="居住地址">
					 <template slot-scope="scope">
							   {{scope.row.address}}
							 </template>
						</el-table-column>
						<el-table-column
						  :sortable="contents.tableSortable" :align="contents.tableAlign"
						  prop="collect"
						  header-align="center"
						  label="收藏状态">
						  <template slot-scope="scope">
							<span style="margin-right:10px">{{scope.row.collect=='已收藏'?'已收藏':'未收藏'}}</span>
							<el-button v-if="scope.row.collect!='已收藏'" type="text" icon="el-icon-edit" size="small" @click="doCollect(scope.row.id)">点击收藏</el-button>
						  </template>
						</el-table-column>
              <el-table-column width="300" :align="contents.tableAlign"
                header-align="center"
                label="操作">
                <template slot-scope="scope">    
				<el-button  type="success" size="mini" @click="collHandler(scope.row.id)">关联</el-button>
                <el-button  type="primary" size="mini" @click="addOrUpdateHandler(scope.row.id)">修改</el-button>

                <el-button  type="danger" size="mini" @click="deleteHandler(scope.row.id)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
                                                                                                                                                                                                <el-pagination
          clsss="pages"
          :layout="layouts"
          @size-change="sizeChangeHandle"
          @current-change="currentChangeHandle"
          :current-page="pageIndex"
          :page-sizes="[10, 20, 50, 100]"
          :page-size="Number(contents.pageEachNum)"
          :total="totalPage"
          :small="contents.pageStyle"
          class="pagination-content"
          :background="contents.pageBtnBG"
          :style="{textAlign:contents.pagePosition==1?'left':contents.pagePosition==2?'center':'right'}"
        ></el-pagination>
      </div>
    </div>
    <!-- 添加/修改页面  将父组件的search方法传递给子组件-->
    <add-or-update v-if="addOrUpdateFlag" :parent="this" ref="addOrUpdate"></add-or-update>
    <collect-list  v-if="collectFlag" :parent="this" ref="collectList"></collect-list>
      </div>
</template>
<script>
import AddOrUpdate from "./add-or-update";
import CollectList from "./collect-list.vue";
export default {
  data() {
    return {
                                                                                                                                                                                                                                                      searchForm: {
        key: ""
      },
      form:{},
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
      dataListSelections: [],
      showFlag: true,
      sfshVisiable: false,
      shForm: {},
      chartVisiable: false,
      addOrUpdateFlag:false,
      collectFlag:false,
            contents:{"searchBtnFontColor":"rgba(17, 14, 14, 1)","pagePosition":"2","inputFontSize":"14px","inputBorderRadius":"4px","tableBtnDelFontColor":"#333","tableBtnIconPosition":"1","searchBtnHeight":"40px","inputIconColor":"#C0C4CC","searchBtnBorderRadius":"8px","tableStripe":false,"btnAdAllWarnFontColor":"rgba(30, 144, 255, 1)","tableBtnDelBgColor":"rgba(245, 83, 185, 0.72)","searchBtnIcon":"1","tableSize":"medium","searchBtnBorderStyle":"solid","tableSelection":true,"searchBtnBorderWidth":"1px","tableContentFontSize":"14px","searchBtnBgColor":"rgba(232, 198, 111, 1)","inputTitleSize":"13px","btnAdAllBorderColor":"rgba(232, 198, 111, 1)","pageJumper":true,"btnAdAllIconPosition":"1","searchBoxPosition":"2","tableBtnDetailFontColor":"rgba(52, 51, 47, 0.93)","tableBtnHeight":"40px","pagePager":true,"searchBtnBorderColor":"#DCDFE6","tableHeaderFontColor":"#909399","inputTitle":"1","tableBtnBorderRadius":"8px","btnAdAllFont":"1","btnAdAllDelFontColor":"rgba(242, 22, 22, 1)","tableBtnIcon":"1","btnAdAllHeight":"40px","btnAdAllWarnBgColor":"#fff","btnAdAllBorderWidth":"1px","tableStripeFontColor":"#606266","tableBtnBorderStyle":"solid","inputHeight":"40px","btnAdAllBorderRadius":"8px","btnAdAllDelBgColor":"#fff","pagePrevNext":true,"btnAdAllAddBgColor":"#fff","searchBtnFont":"1","tableIndex":false,"btnAdAllIcon":"1","tableSortable":true,"pageSizes":true,"tableFit":true,"pageBtnBG":true,"searchBtnFontSize":"14px","tableBtnEditBgColor":"rgba(102, 130, 214, 0.51)","inputBorderWidth":"1px","inputFontPosition":"2","inputFontColor":"rgba(20, 20, 19, 1)","pageEachNum":10,"tableHeaderBgColor":"rgba(228, 221, 221, 0.17)","inputTitleColor":"#333","btnAdAllBoxPosition":"2","tableBtnDetailBgColor":"rgba(232, 198, 111, 1)","inputIcon":"0","searchBtnIconPosition":"2","btnAdAllFontSize":"14px","inputBorderStyle":"solid","inputBgColor":"rgba(228, 221, 221, 0.17)","pageStyle":false,"pageTotal":true,"btnAdAllAddFontColor":"rgba(41, 34, 32, 0.89)","tableBtnFont":"1","tableContentFontColor":"#606266","inputBorderColor":"#DCDFE6","tableShowHeader":true,"tableBtnFontSize":"14px","tableBtnBorderColor":"#DCDFE6","inputIconPosition":"1","tableBorder":true,"btnAdAllBorderStyle":"solid","tableBtnBorderWidth":"1px","tableStripeBgColor":"rgba(232, 198, 111, 1)","tableBtnEditFontColor":"#333","tableAlign":"center"},
      layouts: '',
		json_fields: {
		'姓名': 'name',
		'手机号': 'phone',
		'电子邮件地址': 'email',
		'社交媒体账号': 'smaccounts',
		'居住地址': 'address',
		'收藏状态': 'collect',
	  }

    };
  },
  created() {
    this.init();
    this.getDataList();
    this.contentStyleChange()
  },
  mounted() {

  },
  filters: {
    htmlfilter: function (val) {
      return val.replace(/<[^>]*>/g).replace(/undefined/g,'');
    }
  },
  components: {
    AddOrUpdate,
    CollectList,
      },
  methods: {
    contentStyleChange() {
      this.contentSearchStyleChange()
      this.contentBtnAdAllStyleChange()
      this.contentSearchBtnStyleChange()
      this.contentTableBtnStyleChange()
      this.contentPageStyleChange()
    },
    contentSearchStyleChange() {
      this.$nextTick(()=>{
        document.querySelectorAll('.form-content .slt .el-input__inner').forEach(el=>{
          let textAlign = 'left'
          if(this.contents.inputFontPosition == 2) textAlign = 'center'
          if(this.contents.inputFontPosition == 3) textAlign = 'right'
          el.style.textAlign = textAlign
          el.style.height = this.contents.inputHeight
          el.style.lineHeight = this.contents.inputHeight
          el.style.color = this.contents.inputFontColor
          el.style.fontSize = this.contents.inputFontSize
          el.style.borderWidth = this.contents.inputBorderWidth
          el.style.borderStyle = this.contents.inputBorderStyle
          el.style.borderColor = this.contents.inputBorderColor
          el.style.borderRadius = this.contents.inputBorderRadius
          el.style.backgroundColor = this.contents.inputBgColor
        })
        if(this.contents.inputTitle) {
          document.querySelectorAll('.form-content .slt .el-form-item__label').forEach(el=>{
            el.style.color = this.contents.inputTitleColor
            el.style.fontSize = this.contents.inputTitleSize
            el.style.lineHeight = this.contents.inputHeight
          })
        }
        setTimeout(()=>{
          document.querySelectorAll('.form-content .slt .el-input__prefix').forEach(el=>{
            el.style.color = this.contents.inputIconColor
            el.style.lineHeight = this.contents.inputHeight
          })
          document.querySelectorAll('.form-content .slt .el-input__suffix').forEach(el=>{
            el.style.color = this.contents.inputIconColor
            el.style.lineHeight = this.contents.inputHeight
          })
          document.querySelectorAll('.form-content .slt .el-input__icon').forEach(el=>{
            el.style.lineHeight = this.contents.inputHeight
          })
        },10)

      })
    },
    // 搜索按钮
    contentSearchBtnStyleChange() {
      this.$nextTick(()=>{
        document.querySelectorAll('.form-content .slt .el-button--success').forEach(el=>{
          el.style.height = this.contents.searchBtnHeight
          el.style.color = this.contents.searchBtnFontColor
          el.style.fontSize = this.contents.searchBtnFontSize
          el.style.borderWidth = this.contents.searchBtnBorderWidth
          el.style.borderStyle = this.contents.searchBtnBorderStyle
          el.style.borderColor = this.contents.searchBtnBorderColor
          el.style.borderRadius = this.contents.searchBtnBorderRadius
          el.style.backgroundColor = this.contents.searchBtnBgColor
        })
      })
    },
    // 新增、批量删除
    contentBtnAdAllStyleChange() {
      this.$nextTick(()=>{
        document.querySelectorAll('.form-content .ad .el-button--success').forEach(el=>{
          el.style.height = this.contents.btnAdAllHeight
          el.style.color = this.contents.btnAdAllAddFontColor
          el.style.fontSize = this.contents.btnAdAllFontSize
          el.style.borderWidth = this.contents.btnAdAllBorderWidth
          el.style.borderStyle = this.contents.btnAdAllBorderStyle
          el.style.borderColor = this.contents.btnAdAllBorderColor
          el.style.borderRadius = this.contents.btnAdAllBorderRadius
          el.style.backgroundColor = this.contents.btnAdAllAddBgColor
        })
        document.querySelectorAll('.form-content .ad .el-button--danger').forEach(el=>{
          el.style.height = this.contents.btnAdAllHeight
          el.style.color = this.contents.btnAdAllDelFontColor
          el.style.fontSize = this.contents.btnAdAllFontSize
          el.style.borderWidth = this.contents.btnAdAllBorderWidth
          el.style.borderStyle = this.contents.btnAdAllBorderStyle
          el.style.borderColor = this.contents.btnAdAllBorderColor
          el.style.borderRadius = this.contents.btnAdAllBorderRadius
          el.style.backgroundColor = this.contents.btnAdAllDelBgColor
        })
        document.querySelectorAll('.form-content .ad .el-button--warning').forEach(el=>{
          el.style.height = this.contents.btnAdAllHeight
          el.style.color = this.contents.btnAdAllWarnFontColor
          el.style.fontSize = this.contents.btnAdAllFontSize
          el.style.borderWidth = this.contents.btnAdAllBorderWidth
          el.style.borderStyle = this.contents.btnAdAllBorderStyle
          el.style.borderColor = this.contents.btnAdAllBorderColor
          el.style.borderRadius = this.contents.btnAdAllBorderRadius
          el.style.backgroundColor = this.contents.btnAdAllWarnBgColor
        })
      })
    },
    // 表格
    rowStyle({ row, rowIndex}) {
      if (rowIndex % 2 == 1) {
        if(this.contents.tableStripe) {
          return {color:this.contents.tableStripeFontColor}
        }
      } else {
        return ''
      }
    },
    cellStyle({ row, rowIndex}){
      if (rowIndex % 2 == 1) {
        if(this.contents.tableStripe) {
          return {backgroundColor:this.contents.tableStripeBgColor}
        }
      } else {
        return ''
      }
    },
    headerRowStyle({ row, rowIndex}){
      return {color: this.contents.tableHeaderFontColor}
    },
    headerCellStyle({ row, rowIndex}){
      return {backgroundColor: this.contents.tableHeaderBgColor}
    },
    // 表格按钮
    contentTableBtnStyleChange(){
      // this.$nextTick(()=>{
      //   setTimeout(()=>{
      //     document.querySelectorAll('.table-content .tables .el-table__body .el-button--success').forEach(el=>{
      //       el.style.height = this.contents.tableBtnHeight
      //       el.style.color = this.contents.tableBtnDetailFontColor
      //       el.style.fontSize = this.contents.tableBtnFontSize
      //       el.style.borderWidth = this.contents.tableBtnBorderWidth
      //       el.style.borderStyle = this.contents.tableBtnBorderStyle
      //       el.style.borderColor = this.contents.tableBtnBorderColor
      //       el.style.borderRadius = this.contents.tableBtnBorderRadius
      //       el.style.backgroundColor = this.contents.tableBtnDetailBgColor
      //     })
      //     document.querySelectorAll('.table-content .tables .el-table__body .el-button--primary').forEach(el=>{
      //       el.style.height = this.contents.tableBtnHeight
      //       el.style.color = this.contents.tableBtnEditFontColor
      //       el.style.fontSize = this.contents.tableBtnFontSize
      //       el.style.borderWidth = this.contents.tableBtnBorderWidth
      //       el.style.borderStyle = this.contents.tableBtnBorderStyle
      //       el.style.borderColor = this.contents.tableBtnBorderColor
      //       el.style.borderRadius = this.contents.tableBtnBorderRadius
      //       el.style.backgroundColor = this.contents.tableBtnEditBgColor
      //     })
      //     document.querySelectorAll('.table-content .tables .el-table__body .el-button--danger').forEach(el=>{
      //       el.style.height = this.contents.tableBtnHeight
      //       el.style.color = this.contents.tableBtnDelFontColor
      //       el.style.fontSize = this.contents.tableBtnFontSize
      //       el.style.borderWidth = this.contents.tableBtnBorderWidth
      //       el.style.borderStyle = this.contents.tableBtnBorderStyle
      //       el.style.borderColor = this.contents.tableBtnBorderColor
      //       el.style.borderRadius = this.contents.tableBtnBorderRadius
      //       el.style.backgroundColor = this.contents.tableBtnDelBgColor
      //     })

      //   }, 50)
      // })
    },
    // 分页
    contentPageStyleChange(){
      let arr = []

      if(this.contents.pageTotal) arr.push('total')
      if(this.contents.pageSizes) arr.push('sizes')
      if(this.contents.pagePrevNext){
        arr.push('prev')
        if(this.contents.pagePager) arr.push('pager')
        arr.push('next')
      }
      if(this.contents.pageJumper) arr.push('jumper')
      this.layouts = arr.join()
      this.contents.pageEachNum = 10
    },

                init () {
                                                                                                                                                                                                                                                                                                                                                        },
    search() {
      this.pageIndex = 1;
      this.getDataList();
    },
	//导入
	handleExcelImportSuccess () {
	  this.$message.success("导入成功");
	  this.getDataList(); //重新加载数据
	},
    // 获取数据列表
    getDataList() {
      this.dataListLoading = true;
      let params = {
        page: this.pageIndex,
        limit: this.pageSize,
        sort: 'id',
      }
                                                                                      if(this.searchForm.name!='' && this.searchForm.name!=undefined){
            params['name'] = '%' + this.searchForm.name + '%'
          }
                                                            if(this.searchForm.phone!='' && this.searchForm.phone!=undefined){
            params['phone'] = '%' + this.searchForm.phone + '%'
          }
		  
		  if(this.searchForm.collect!='' && this.searchForm.collect!=undefined){
		    params['collect'] = this.searchForm.collect
		  }
        
        this.$http({
        url: "ab/page",
        method: "get",
        params: params
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.dataList = data.data.list;
          this.totalPage = data.data.total;
        } else {
          this.dataList = [];
          this.totalPage = 0;
        }
        this.dataListLoading = false;
      });
    },
    // 每页数
    sizeChangeHandle(val) {
      this.pageSize = val;
      this.pageIndex = 1;
      this.getDataList();
    },
    // 当前页
    currentChangeHandle(val) {
      this.pageIndex = val;
      this.getDataList();
    },
    // 多选
    selectionChangeHandler(val) {
      this.dataListSelections = val;
    },
    // 添加/修改
    addOrUpdateHandler(id,type) {
      this.showFlag = false;
      this.addOrUpdateFlag = true;
      this.crossAddOrUpdateFlag = false;
      if(type!='info'){
        type = 'else';
      }
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(id,type);
      });
    },
	// 关联
	collHandler(id,type) {
	  this.showFlag = false;
	  this.collectFlag = true;
	  this.$nextTick(() => {
	    this.$refs.collectList.init(id,type);
	  });
	},
    // 删除
    deleteHandler(id) {
      var ids = id
        ? [Number(id)]
        : this.dataListSelections.map(item => {
            return Number(item.id);
          });
      this.$confirm(`确定进行[${id ? "删除" : "批量删除"}]操作?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.$http({
          url: "ab/delete",
          method: "post",
          data: ids
        }).then(({ data }) => {
          if (data && data.code === 0) {
            this.$message({
              message: "操作成功",
              type: "success",
              duration: 1500,
              onClose: () => {
                this.search();
              }
            });
          } else {
            this.$message.error(data.msg);
          }
        });
      });
    },
	doCollect(id){
	  this.$confirm(`确定进行["收藏"]操作?`, "提示", {
		confirmButtonText: "确定",
		cancelButtonText: "取消",
		type: "warning"
	  }).then(() => {
		this.$http({
		  url: "ab/doCollect",
		  method: "post",
		  data: id
		}).then(({ data }) => {
		  if (data && data.code === 0) {
			this.$message({
			  message: "操作成功",
			  type: "success",
			  duration: 1500,
			  onClose: () => {
				this.search();
			  }
			});
		  } else {
			this.$message.error(data.msg);
		  }
		});
	  });
		
	},
  }

};
</script>
<style lang="scss" scoped>
  .slt {
    margin: 0 !important;
    display: flex;
  }

  .ad {
    margin: 0 !important;
    display: flex;
  }

  .pages {
    & /deep/ el-pagination__sizes{
      & /deep/ el-input__inner {
        height: 22px;
        line-height: 22px;
      }
    }
  }
  

  .el-button+.el-button {
    margin:0;
  } 

  .tables {
	& /deep/ .el-button--success {
		height: 40px;
		color: rgba(52, 51, 47, 0.93);
		font-size: 14px;
		border-width: 1px;
		border-style: solid;
		border-color: #DCDFE6;
		border-radius: 8px;
		background-color: rgba(232, 198, 111, 1);
	}
	
	& /deep/ .el-button--primary {
		height: 40px;
		color: #333;
		font-size: 14px;
		border-width: 1px;
		border-style: solid;
		border-color: #DCDFE6;
		border-radius: 8px;
		background-color: rgba(102, 130, 214, 0.51);
	}
	
	& /deep/ .el-button--danger {
		height: 40px;
		color: #333;
		font-size: 14px;
		border-width: 1px;
		border-style: solid;
		border-color: #DCDFE6;
		border-radius: 8px;
		background-color: rgba(245, 83, 185, 0.72);
	}

    & /deep/ .el-button {
      margin: 4px;
    }
  }

</style>
