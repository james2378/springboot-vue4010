<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','candidate_information') || $check_field('add','candidate_information') || $check_field('set','candidate_information')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="考生信息" prop="candidate_information">
						<el-select v-if="user_group === '管理员' || (form['admission_information_id'] && $check_field('set','candidate_information')) || (!form['admission_information_id'] && $check_field('add','candidate_information'))" id="candidate_information" v-model="form['candidate_information']" :disabled="disabledObj['candidate_information_isDisabled']">
							<el-option v-for="o in list_user_candidate_information" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
						<el-select v-else-if="$check_field('get','candidate_information')" id="candidate_information" v-model="form['candidate_information']" :disabled="true">
							<el-option v-for="o in list_user_candidate_information" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','full_name') || $check_field('add','full_name') || $check_field('set','full_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="姓名" prop="full_name">
					<el-input id="full_name" v-model="form['full_name']" placeholder="请输入姓名"
							  v-if="user_group === '管理员' || (form['admission_information_id'] && $check_field('set','full_name')) || (!form['admission_information_id'] && $check_field('add','full_name'))" :disabled="disabledObj['full_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','full_name')">{{form['full_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','gender') || $check_field('add','gender') || $check_field('set','gender')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="性别" prop="gender">
					<el-input id="gender" v-model="form['gender']" placeholder="请输入性别"
							  v-if="user_group === '管理员' || (form['admission_information_id'] && $check_field('set','gender')) || (!form['admission_information_id'] && $check_field('add','gender'))" :disabled="disabledObj['gender_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','gender')">{{form['gender']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','student_number') || $check_field('add','student_number') || $check_field('set','student_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="学号" prop="student_number">
					<el-input id="student_number" v-model="form['student_number']" placeholder="请输入学号"
							  v-if="user_group === '管理员' || (form['admission_information_id'] && $check_field('set','student_number')) || (!form['admission_information_id'] && $check_field('add','student_number'))" :disabled="disabledObj['student_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','student_number')">{{form['student_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','faculty') || $check_field('add','faculty') || $check_field('set','faculty')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="院系" prop="faculty">
					<el-input id="faculty" v-model="form['faculty']" placeholder="请输入院系"
							  v-if="user_group === '管理员' || (form['admission_information_id'] && $check_field('set','faculty')) || (!form['admission_information_id'] && $check_field('add','faculty'))" :disabled="disabledObj['faculty_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','faculty')">{{form['faculty']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','registration_level') || $check_field('add','registration_level') || $check_field('set','registration_level')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="报考等级" prop="registration_level">
					<el-input id="registration_level" v-model="form['registration_level']" placeholder="请输入报考等级"
							  v-if="user_group === '管理员' || (form['admission_information_id'] && $check_field('set','registration_level')) || (!form['admission_information_id'] && $check_field('add','registration_level'))" :disabled="disabledObj['registration_level_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','registration_level')">{{form['registration_level']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','examinee_number_') || $check_field('add','examinee_number_') || $check_field('set','examinee_number_')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="考生考号" prop="examinee_number_">
					<el-input id="examinee_number_" v-model="form['examinee_number_']" placeholder="请输入考生考号"
							  v-if="user_group === '管理员' || (form['admission_information_id'] && $check_field('set','examinee_number_')) || (!form['admission_information_id'] && $check_field('add','examinee_number_'))" :disabled="disabledObj['examinee_number__isDisabled']"></el-input>
					<div v-else-if="$check_field('get','examinee_number_')">{{form['examinee_number_']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','centre_name_') || $check_field('add','centre_name_') || $check_field('set','centre_name_')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="考场名称" prop="centre_name_">
					<el-input id="centre_name_" v-model="form['centre_name_']" placeholder="请输入考场名称"
							  v-if="user_group === '管理员' || (form['admission_information_id'] && $check_field('set','centre_name_')) || (!form['admission_information_id'] && $check_field('add','centre_name_'))" :disabled="disabledObj['centre_name__isDisabled']"></el-input>
					<div v-else-if="$check_field('get','centre_name_')">{{form['centre_name_']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','examination_time') || $check_field('add','examination_time') || $check_field('set','examination_time')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="考试时间" prop="examination_time">
					<el-date-picker :disabled="disabledObj['examination_time_isDisabled']" v-if="user_group === '管理员' || (form['admission_information_id'] && $check_field('set','examination_time')) || (!form['admission_information_id'] && $check_field('add','examination_time'))" id="examination_time"
						v-model="form['examination_time']" type="datetime" placeholder="选择日期时间">
					</el-date-picker>
					<div v-else-if="$check_field('get','examination_time')">{{form['examination_time']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','remark_information') || $check_field('add','remark_information') || $check_field('set','remark_information')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="备注信息" prop="remark_information">
					<el-input type="textarea" id="remark_information" v-model="form['remark_information']" placeholder="请输入备注信息"
						v-if="user_group === '管理员' || (form['admission_information_id'] && $check_field('set','remark_information')) || (!form['admission_information_id'] && $check_field('add','remark_information'))" :disabled="disabledObj['remark_information_isDisabled']"></el-input>
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
				field: "admission_information_id",
				url_add: "~/api/admission_information/add?",
				url_set: "~/api/admission_information/set?",
				url_get_obj: "~/api/admission_information/get_obj?",
				url_upload: "~/api/admission_information/upload?",

				query: {
					"admission_information_id": 0,
				},

				form: {
					"candidate_information": 0, // 考生信息
					"full_name":'', // 姓名
					"gender":'', // 性别
					"student_number":'', // 学号
					"faculty":'', // 院系
					"registration_level":'', // 报考等级
					"examinee_number_":'', // 考生考号
					"centre_name_":'', // 考场名称
					"examination_time":'', // 考试时间
					"remark_information":'', // 备注信息
					"admission_information_id": 0, // ID

				},
				disabledObj:{
					"candidate_information_isDisabled": false,
					"full_name_isDisabled": false,
					"gender_isDisabled": false,
					"student_number_isDisabled": false,
					"faculty_isDisabled": false,
					"registration_level_isDisabled": false,
					"examinee_number__isDisabled": false,
					"centre_name__isDisabled": false,
					"examination_time_isDisabled": false,
					"remark_information_isDisabled": false,
				},
				// 用户列表
				list_user_candidate_information: [],

			}
		},
		methods: {
			/**
			 * 获取考生用户用户列表
			 */
			async get_list_user_candidate_information() {
                // if(this.user_group !== "管理员" && this.form["candidate_information"] === 0) {
                //     this.form["candidate_information"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=考生用户");
                if(json.result && json.result.list){
                    this.list_user_candidate_information = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
			get_user_candidate_information(id){
				var obj = this.list_user_candidate_information.getObj({"user_id":id});
				var ret = "";
				if(obj){
					if(obj.nickname){
						ret = obj.nickname;}
					else{
						ret = obj.username;
					}
				}
				return ret;
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				// 获取缓存数据附加
				form = $.db.get("form");
				$.push(this.form ,form);
				/**
				* 请求列表前
				* @param {Object} param
				*/
				var user_group = this.user.user_group;
				if (user_group !== "管理员") {
					switch (user_group) {
						case "考生信息":
							if(param["candidate_information"] > 0){
								param["candidate_information"] = this.user.user_id;
							}
							break;
					}
				}
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
				this.form["examination_time"] = this.$toTime(parseInt(this.form["examination_time"]),"yyyy-MM-dd hh:mm:ss")
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){
				if(parseInt(this.form["examination_time"]) > 9999){
					this.form["examination_time"] = this.$toTime(parseInt(this.form["examination_time"]),"yyyy-MM-dd hh:mm:ss")
				}

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/admission_information/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/admission_information/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/admission_information/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/admission_information/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/admission_information/view','get');
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
			this.get_list_user_candidate_information();
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
