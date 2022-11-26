<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="商品名称" prop="goodName">
        <el-input
          v-model="queryParams.goodName"
          placeholder="请输入商品名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品分类" prop="goodClass">
        <el-select v-model="queryParams.goodClass" placeholder="请选择商品分类" clearable>
          <el-option
            v-for="dict in dict.type.gsx_good_class"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="api类型" prop="apiType">
        <el-input
          v-model="queryParams.apiType"
          placeholder="请输入api类型"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品删除状态" prop="isDeleted">
        <el-input
          v-model="queryParams.isDeleted"
          placeholder="请输入商品删除状态"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品上架状态" prop="isPublish">
        <el-input
          v-model="queryParams.isPublish"
          placeholder="请输入商品上架状态"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建者" prop="createBy">
        <el-input
          v-model="queryParams.createBy"
          placeholder="请输入创建者"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker clearable
          v-model="queryParams.createTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="更新者" prop="updateBy">
        <el-input
          v-model="queryParams.updateBy"
          placeholder="请输入更新者"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="更新时间" prop="updateTime">
        <el-date-picker clearable
          v-model="queryParams.updateTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择更新时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['gsx:good:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['gsx:good:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['gsx:good:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['gsx:good:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="goodList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="商品id" align="center" prop="goodId" />
      <el-table-column label="商品名称" align="center" prop="goodName" />
      <el-table-column label="商品分类" align="center" prop="goodClass">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.gsx_good_class" :value="scope.row.goodClass"/>
        </template>
      </el-table-column>
      <el-table-column label="商品图片" align="center" prop="goodImage" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.goodImage" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="商品详情" align="center" prop="goodDetail" />
      <el-table-column label="商品库存" align="center" prop="goodStock" />
      <el-table-column label="商品价格" align="center" prop="goodPrice" />
      <el-table-column label="商品成本价" align="center" prop="goodCostPrice" />
      <el-table-column label="商品划线价" align="center" prop="goodMarkPrice" />
      <el-table-column label="商品库存预警值" align="center" prop="lowStock" />
      <el-table-column label="api类型" align="center" prop="apiType" />
      <el-table-column label="api地址" align="center" prop="apiUrl" />
      <el-table-column label="显示顺序" align="center" prop="orderNum" />
      <el-table-column label="商品删除状态" align="center" prop="isDeleted">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.gsx_good_deleted" :value="scope.row.isDeleted"/>
        </template>
      </el-table-column>
      <el-table-column label="商品上架状态" align="center" prop="isPublish">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.gsx_good_publish" :value="scope.row.isPublish"/>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['gsx:good:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['gsx:good:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改GSX商品对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="商品名称" prop="goodName">
          <el-input v-model="form.goodName" placeholder="请输入商品名称" />
        </el-form-item>
        <el-form-item label="商品分类" prop="goodClass">
          <el-select v-model="form.goodClass" placeholder="请选择商品分类">
            <el-option
              v-for="dict in dict.type.gsx_good_class"
              :key="dict.value"
              :label="dict.label"
:value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="商品图片">
          <image-upload v-model="form.goodImage"/>
        </el-form-item>
        <el-form-item label="商品详情" prop="goodDetail">
          <el-input v-model="form.goodDetail" placeholder="请输入商品详情" />
        </el-form-item>
        <el-form-item label="商品库存" prop="goodStock">
          <el-input v-model="form.goodStock" placeholder="请输入商品库存" />
        </el-form-item>
        <el-form-item label="商品价格" prop="goodPrice">
          <el-input v-model="form.goodPrice" placeholder="请输入商品价格" />
        </el-form-item>
        <el-form-item label="商品成本价" prop="goodCostPrice">
          <el-input v-model="form.goodCostPrice" placeholder="请输入商品成本价" />
        </el-form-item>
        <el-form-item label="商品划线价" prop="goodMarkPrice">
          <el-input v-model="form.goodMarkPrice" placeholder="请输入商品划线价" />
        </el-form-item>
        <el-form-item label="商品库存预警值" prop="lowStock">
          <el-input v-model="form.lowStock" placeholder="请输入商品库存预警值" />
        </el-form-item>
        <el-form-item label="api类型" prop="apiType">
          <el-input v-model="form.apiType" placeholder="请输入api类型" />
        </el-form-item>
        <el-form-item label="api地址" prop="apiUrl">
          <el-input v-model="form.apiUrl" placeholder="请输入api地址" />
        </el-form-item>
        <el-form-item label="显示顺序" prop="orderNum">
          <el-input v-model="form.orderNum" placeholder="请输入显示顺序" />
        </el-form-item>
        <el-form-item label="商品删除状态" prop="isDeleted">
          <el-input v-model="form.isDeleted" placeholder="请输入商品删除状态" />
        </el-form-item>
        <el-form-item label="商品上架状态" prop="isPublish">
          <el-input v-model="form.isPublish" placeholder="请输入商品上架状态" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listGood, getGood, delGood, addGood, updateGood } from "@/api/gsx/good";

export default {
  name: "Good",
  dicts: ['gsx_good_class'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // GSX商品表格数据
      goodList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        goodName: null,
        goodClass: null,
        apiType: null,
        isDeleted: null,
        isPublish: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询GSX商品列表 */
    getList() {
      this.loading = true;
      listGood(this.queryParams).then(response => {
        this.goodList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        goodId: null,
        goodName: null,
        goodClass: null,
        goodImage: null,
        goodDetail: null,
        goodStock: null,
        goodPrice: null,
        goodCostPrice: null,
        goodMarkPrice: null,
        lowStock: null,
        apiType: null,
        apiUrl: null,
        orderNum: null,
        isDeleted: null,
        isPublish: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.goodId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加GSX商品";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const goodId = row.goodId || this.ids
      getGood(goodId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改GSX商品";
      });
    },
    /** 提交按钮 */
    submitForm() {
      console.log(this.form["goodImage"].length)
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.goodId != null) {
            updateGood(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addGood(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const goodIds = row.goodId || this.ids;
      this.$modal.confirm('是否确认删除GSX商品编号为"' + goodIds + '"的数据项？').then(function() {
        return delGood(goodIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('gsx/good/export', {
        ...this.queryParams
      }, `good_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
