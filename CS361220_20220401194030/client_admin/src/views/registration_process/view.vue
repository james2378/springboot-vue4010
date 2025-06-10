<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','title_name') || $check_field('add','title_name') || $check_field('set','title_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="标题名称" prop="title_name">
					<el-input id="title_name" v-model="form['title_name']" placeholder="请输入标题名称"
							  v-if="user_group === '管理员' || (form['registration_process_id'] && $check_field('set','title_name')) || (!form['registration_process_id'] && $check_field('add','title_name'))" :disabled="disabledObj['title_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','title_name')">{{form['title_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','release_date') || $check_field('add','release_date') || $check_field('set','release_date')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="发布日期" prop="release_date">
					<el-date-picker :disabled="disabledObj['release_date_isDisabled']" v-if="user_group === '管理员' || (form['registration_process_id'] && $check_field('set','release_date')) || (!form['registration_process_id'] && $check_field('add','release_date'))" id="release_date"
						v-model="form['release_date']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','release_date')">{{form['release_date']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','cover_photo_') || $check_field('add','cover_photo_') || $check_field('set','cover_photo_')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="封面图片" prop="cover_photo_">
					<el-upload :disabled="disabledObj['cover_photo__isDisabled']" id="cover_photo_" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_cover_photo_"
						:show-file-list="false" v-if="user_group === '管理员' || (form['registration_process_id'] && $check_field('set','cover_photo_')) || (!form['registration_process_id'] && $check_field('add','cover_photo_'))">
						<img v-if="form['cover_photo_']" :src="$fullUrl(form['cover_photo_'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','cover_photo_')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['cover_photo_'])" :preview-src-list="[$fullUrl(form['cover_photo_'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','registration_criteria') || $check_field('add','registration_criteria') || $check_field('set','registration_criteria')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="报名准则" prop="registration_criteria">
					<el-input type="textarea" id="registration_criteria" v-model="form['registration_criteria']" placeholder="请输入报名准则"
						v-if="user_group === '管理员' || (form['registration_process_id'] && $check_field('set','registration_criteria')) || (!form['registration_process_id'] && $check_field('add','registration_criteria'))" :disabled="disabledObj['registration_criteria_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','registration_criteria')">{{form['registration_criteria']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','detailed_process') || $check_field('add','detailed_process') || $check_field('set','detailed_process')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="详细流程" prop="detailed_process">
					<el-input type="textarea" id="detailed_process" v-model="form['detailed_process']" placeholder="请输入详细流程"
						v-if="user_group === '管理员' || (form['registration_process_id'] && $check_field('set','detailed_process')) || (!form['registration_process_id'] && $check_field('add','detailed_process'))" :disabled="disabledObj['detailed_process_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','detailed_process')">{{form['detailed_process']}}</div>
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
				field: "registration_process_id",
				url_add: "~/api/registration_process/add?",
				url_set: "~/api/registration_process/set?",
				url_get_obj: "~/api/registration_process/get_obj?",
				url_upload: "~/api/registration_process/upload?",

				query: {
					"registration_process_id": 0,
				},

				form: {
					"title_name":'', // 标题名称
					"release_date":'', // 发布日期
					"cover_photo_":'', // 封面图片
					"registration_criteria":'', // 报名准则
					"detailed_process":'', // 详细流程
					"registration_process_id": 0, // ID

				},
				disabledObj:{
					"title_name_isDisabled": false,
					"release_date_isDisabled": false,
					"cover_photo__isDisabled": false,
					"registration_criteria_isDisabled": false,
					"detailed_process_isDisabled": false,
				},

			}
		},
		methods: {
			/**
			 * 上传封面图片
			 * @param {Object} param图片参数
			 */
			upload_cover_photo_(param){
				this.uploadFile(param.file, "cover_photo_");
			},

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
        if (this.form["release_date"].indexOf("-")===-1){
          this.form["release_date"] = this.$toTime(parseInt(this.form["release_date"]),"yyyy-MM-dd")
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
				if(parseInt(this.form["release_date"]) > 9999){
					this.form["release_date"] = this.$toTime(parseInt(this.form["release_date"]),"yyyy-MM-dd")
				}

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/registration_process/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/registration_process/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/registration_process/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/registration_process/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/registration_process/view','get');
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
