<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','department_name') || $check_field('add','department_name') || $check_field('set','department_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="院系名称" prop="department_name">
					<el-input id="department_name" v-model="form['department_name']" placeholder="请输入院系名称"
							  v-if="user_group === '管理员' || (form['department_management_id'] && $check_field('set','department_name')) || (!form['department_management_id'] && $check_field('add','department_name'))" :disabled="disabledObj['department_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','department_name')">{{form['department_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','department_manager') || $check_field('add','department_manager') || $check_field('set','department_manager')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="院系管理人" prop="department_manager">
					<el-input id="department_manager" v-model="form['department_manager']" placeholder="请输入院系管理人"
							  v-if="user_group === '管理员' || (form['department_management_id'] && $check_field('set','department_manager')) || (!form['department_management_id'] && $check_field('add','department_manager'))" :disabled="disabledObj['department_manager_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','department_manager')">{{form['department_manager']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','department_profile') || $check_field('add','department_profile') || $check_field('set','department_profile')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="院系简介" prop="department_profile">
					<el-input type="textarea" id="department_profile" v-model="form['department_profile']" placeholder="请输入院系简介"
						v-if="user_group === '管理员' || (form['department_management_id'] && $check_field('set','department_profile')) || (!form['department_management_id'] && $check_field('add','department_profile'))" :disabled="disabledObj['department_profile_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','department_profile')">{{form['department_profile']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','department_description') || $check_field('add','department_description') || $check_field('set','department_description')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="院系描述" prop="department_description">
					<el-input type="textarea" id="department_description" v-model="form['department_description']" placeholder="请输入院系描述"
						v-if="user_group === '管理员' || (form['department_management_id'] && $check_field('set','department_description')) || (!form['department_management_id'] && $check_field('add','department_description'))" :disabled="disabledObj['department_description_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','department_description')">{{form['department_description']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','remark_information') || $check_field('add','remark_information') || $check_field('set','remark_information')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="备注信息" prop="remark_information">
					<el-input type="textarea" id="remark_information" v-model="form['remark_information']" placeholder="请输入备注信息"
						v-if="user_group === '管理员' || (form['department_management_id'] && $check_field('set','remark_information')) || (!form['department_management_id'] && $check_field('add','remark_information'))" :disabled="disabledObj['remark_information_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','remark_information')">{{form['remark_information']}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "department_management_id",
				url_add: "~/api/department_management/add?",
				url_set: "~/api/department_management/set?",
				url_get_obj: "~/api/department_management/get_obj?",
				url_upload: "~/api/department_management/upload?",

				query: {
					"department_management_id": 0,
				},

				form: {
					"department_name":'', // 院系名称
					"department_manager":'', // 院系管理人
					"department_profile":'', // 院系简介
					"department_description":'', // 院系描述
					"remark_information":'', // 备注信息
					"department_management_id": 0, // ID

				},
				disabledObj:{
					"department_name_isDisabled": false,
					"department_manager_isDisabled": false,
					"department_profile_isDisabled": false,
					"department_description_isDisabled": false,
					"remark_information_isDisabled": false,
				},

			}
		},
		methods: {

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							// if(dbKey === "charging_standard"){
							// 	this.form['charging_rules'] = form[dbKey];
							// 	this.disabledObj['charging_rules_isDisabled'] = true;
							// };
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/department_management/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/department_management/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/department_management/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/department_management/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/department_management/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
		}
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}
</style>
