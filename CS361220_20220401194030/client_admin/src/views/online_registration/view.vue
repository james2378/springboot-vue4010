<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','candidate_information') || $check_field('add','candidate_information') || $check_field('set','candidate_information')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="考生信息" prop="candidate_information">
						<div v-if="user_group !== '管理员'">
							{{ get_user_session_candidate_information(form['candidate_information']) }}
							<!--<el-input id="business_name" v-model="form['candidate_information']" placeholder="请输入考生信息"-->
							<!--v-if="user_group === '管理员' || (form['online_registration_id'] && $check_field('set','candidate_information')) || (!form['online_registration_id'] && $check_field('add','candidate_information'))" :disabled="disabledObj['candidate_information_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','candidate_information')">{{form['candidate_information']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['online_registration_id'] && $check_field('set','candidate_information')) || (!form['online_registration_id'] && $check_field('add','candidate_information'))" id="candidate_information" v-model="form['candidate_information']" :disabled="disabledObj['candidate_information_isDisabled']">
								<el-option v-for="o in list_user_candidate_information" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','candidate_information')" id="candidate_information" v-model="form['candidate_information']" :disabled="true">
								<el-option v-for="o in list_user_candidate_information" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="candidate_information" v-model="form['candidate_information']" :disabled="disabledObj['candidate_information_isDisabled']">
							<el-option v-for="o in list_user_candidate_information" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','full_name') || $check_field('add','full_name') || $check_field('set','full_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="姓名" prop="full_name">
					<el-input id="full_name" v-model="form['full_name']" placeholder="请输入姓名"
							  v-if="user_group === '管理员' || (form['online_registration_id'] && $check_field('set','full_name')) || (!form['online_registration_id'] && $check_field('add','full_name'))" :disabled="disabledObj['full_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','full_name')">{{form['full_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','gender') || $check_field('add','gender') || $check_field('set','gender')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="性别" prop="gender">
					<el-input id="gender" v-model="form['gender']" placeholder="请输入性别"
							  v-if="user_group === '管理员' || (form['online_registration_id'] && $check_field('set','gender')) || (!form['online_registration_id'] && $check_field('add','gender'))" :disabled="disabledObj['gender_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','gender')">{{form['gender']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','student_number') || $check_field('add','student_number') || $check_field('set','student_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="学号" prop="student_number">
					<el-input id="student_number" v-model="form['student_number']" placeholder="请输入学号"
							  v-if="user_group === '管理员' || (form['online_registration_id'] && $check_field('set','student_number')) || (!form['online_registration_id'] && $check_field('add','student_number'))" :disabled="disabledObj['student_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','student_number')">{{form['student_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','faculty') || $check_field('add','faculty') || $check_field('set','faculty')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="院系" prop="faculty">
					<el-input id="faculty" v-model="form['faculty']" placeholder="请输入院系"
							  v-if="user_group === '管理员' || (form['online_registration_id'] && $check_field('set','faculty')) || (!form['online_registration_id'] && $check_field('add','faculty'))" :disabled="disabledObj['faculty_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','faculty')">{{form['faculty']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','registration_level') || $check_field('add','registration_level') || $check_field('set','registration_level')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="报考等级" prop="registration_level">
					<el-select id="registration_level" v-model="form['registration_level']"
						v-if="user_group === '管理员' || (form['online_registration_id'] && $check_field('set','registration_level')) || (!form['online_registration_id'] && $check_field('add','registration_level'))">
						<el-option v-for="o in list_registration_level" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','registration_level')">{{form['registration_level']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','upload_photos') || $check_field('add','upload_photos') || $check_field('set','upload_photos')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="上传照片" prop="upload_photos">
					<el-upload :disabled="disabledObj['upload_photos_isDisabled']" id="upload_photos" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_upload_photos"
						:show-file-list="false" v-if="user_group === '管理员' || (form['online_registration_id'] && $check_field('set','upload_photos')) || (!form['online_registration_id'] && $check_field('add','upload_photos'))">
						<img v-if="form['upload_photos']" :src="$fullUrl(form['upload_photos'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','upload_photos')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['upload_photos'])" :preview-src-list="[$fullUrl(form['upload_photos'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','registration_status') || $check_field('add','registration_status') || $check_field('set','registration_status')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="报名状态" prop="registration_status">
					<el-select id="registration_status" v-model="form['registration_status']"
						v-if="user_group === '管理员' || (form['online_registration_id'] && $check_field('set','registration_status')) || (!form['online_registration_id'] && $check_field('add','registration_status'))">
						<el-option v-for="o in list_registration_status" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','registration_status')">{{form['registration_status']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','reply_message') || $check_field('add','reply_message') || $check_field('set','reply_message')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="回复信息" prop="reply_message">
					<el-input type="textarea" id="reply_message" v-model="form['reply_message']" placeholder="请输入回复信息"
						v-if="user_group === '管理员' || (form['online_registration_id'] && $check_field('set','reply_message')) || (!form['online_registration_id'] && $check_field('add','reply_message'))" :disabled="disabledObj['reply_message_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','reply_message')">{{form['reply_message']}}</div>
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
				field: "online_registration_id",
				url_add: "~/api/online_registration/add?",
				url_set: "~/api/online_registration/set?",
				url_get_obj: "~/api/online_registration/get_obj?",
				url_upload: "~/api/online_registration/upload?",

				query: {
					"online_registration_id": 0,
				},

				form: {
					"candidate_information": 0, // 考生信息
					"full_name":'', // 姓名
					"gender":'', // 性别
					"student_number":'', // 学号
					"faculty":'', // 院系
					"registration_level":'', // 报考等级
					"upload_photos":'', // 上传照片
					"registration_status":'', // 报名状态
					"reply_message":'', // 回复信息
					"online_registration_id": 0, // ID

				},
				disabledObj:{
					"candidate_information_isDisabled": false,
					"full_name_isDisabled": false,
					"gender_isDisabled": false,
					"student_number_isDisabled": false,
					"faculty_isDisabled": false,
					"registration_level_isDisabled": false,
					"upload_photos_isDisabled": false,
					"registration_status_isDisabled": false,
					"reply_message_isDisabled": false,
				},
				// 用户列表
				list_user_candidate_information: [],
				// 用户组
				group_user_candidate_information: "",
				//报考等级选项列表
				list_registration_level: ['一级','二级','三级'],
				//报名状态选项列表
				list_registration_status: ['报名失败','报名成功'],

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
			/**
			 * 获取考生用户用户组
			 */
			async get_group_user_candidate_information() {
				this.form["candidate_information"] = this.user.user_id;
				var json = await this.$get("~/api/user_group/get_obj?name=考生用户");
				if(json.result && json.result.obj){
					this.group_user_candidate_information = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_candidate_information(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_candidate_information.source_table+"/get_obj?"
				this.$get(url, user_id, function(res) {
					if (res.result && res.result.obj) {
						var arr = []
						for (let key in res.result.obj) {
							arr.push(key)
						}
						var arrForm = []
						for (let key in _this.form) {
							arrForm.push(key)
						}
						for (var i=0;i<arr.length;i++){
							for (var j=0;j<arrForm.length;j++){
								if (arr[i]===arrForm[j]){
									if (arr[i]!=="candidate_information") {
										_this.form[arrForm[j]] = res.result.obj[arr[i]]
										_this.disabledObj[arrForm[j] + '_isDisabled'] = true
										break;
									}else {
										_this.disabledObj[arrForm[j] + '_isDisabled'] = true
									}
								}
							}
						}
					}
				});
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
			 * 上传上传照片
			 * @param {Object} param图片参数
			 */
			upload_upload_photos(param){
				this.uploadFile(param.file, "upload_photos");
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
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
					bl = this.$check_action('/online_registration/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/online_registration/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/online_registration/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/online_registration/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/online_registration/view','get');
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
			this.get_group_user_candidate_information();
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
