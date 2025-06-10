<template>
	<div class="diy_edit page_online_registration" id="online_registration_edit">
		<div class='warp'>
			<div class='container'>
				<div class='row diy_edit_content_box'>
					<div v-if="$check_field('set','candidate_information') || $check_field('add','candidate_information') || $check_field('get','candidate_information')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>考生信息:
							</span>
						</div>
						<div class="diy_field diy_down">
							<select id="form_candidate_information" :disabled="disabledObj['candidate_information_isDisabled']" v-model="form['candidate_information']" v-if="(form['candidate_information'] && $check_field('set','candidate_information')) || (!form['candidate_information'] && $check_field('add','candidate_information'))" >
								<option v-for="o in list_user_candidate_information" :value="o['user_id']">
									{{o['nickname'] + '-' + o['username']}}
								</option>
							</select>
							<span v-else-if="$check_field('get','candidate_information')">{{ form['candidate_information'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','full_name') || $check_field('add','full_name') || $check_field('get','full_name')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>姓名:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_full_name" v-model="form['full_name']" placeholder="请输入姓名" v-if="(form['full_name'] && $check_field('set','full_name')) || (!form['full_name'] && $check_field('add','full_name'))"  :disabled="disabledObj['full_name_isDisabled']"/>
							<span v-else-if="$check_field('get','full_name')">{{ form['full_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','gender') || $check_field('add','gender') || $check_field('get','gender')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>性别:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_gender" v-model="form['gender']" placeholder="请输入性别" v-if="(form['gender'] && $check_field('set','gender')) || (!form['gender'] && $check_field('add','gender'))"  :disabled="disabledObj['gender_isDisabled']"/>
							<span v-else-if="$check_field('get','gender')">{{ form['full_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','student_number') || $check_field('add','student_number') || $check_field('get','student_number')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>学号:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_student_number" v-model="form['student_number']" placeholder="请输入学号" v-if="(form['student_number'] && $check_field('set','student_number')) || (!form['student_number'] && $check_field('add','student_number'))"  :disabled="disabledObj['student_number_isDisabled']"/>
							<span v-else-if="$check_field('get','student_number')">{{ form['full_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','faculty') || $check_field('add','faculty') || $check_field('get','faculty')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>院系:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_faculty" v-model="form['faculty']" placeholder="请输入院系" v-if="(form['faculty'] && $check_field('set','faculty')) || (!form['faculty'] && $check_field('add','faculty'))"  :disabled="disabledObj['faculty_isDisabled']"/>
							<span v-else-if="$check_field('get','faculty')">{{ form['full_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','registration_level') || $check_field('add','registration_level') || $check_field('get','registration_level')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>报考等级:
							</span>
						</div>
						<!-- 选项 -->
						<div class="diy_field diy_down">
							<select id="form_registration_level" v-model="form['registration_level']" v-if="(form['registration_level'] && $check_field('set','registration_level')) || (!form['registration_level'] && $check_field('add','registration_level'))" >
								<option v-for="o in list_registration_level" :value="o">
									{{o}}
								</option>
							</select>
							<span v-else-if="$check_field('get','registration_level')">{{ form['registration_level'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','upload_photos') || $check_field('add','upload_photos') || $check_field('get','upload_photos')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>上传照片:
							</span>
						</div>
						<!-- 图片 -->
						<input type="file" :disabled="disabledObj['upload_photos_isDisabled']" style="display: none;" id="form_img_upload_photos" title="upload_photos" @change="change_file($event.target.files,'upload_photos')"/>
						<!-- 修改权限 -->
						<div class="diy_field diy_img" v-if="form['upload_photos'] && $check_field('set','upload_photos')">
							<label for="form_img_upload_photos">
								<img :src="$fullUrl(form['upload_photos'])" />
							</label>
						</div>
						<!-- 添加权限 -->
						<div class="diy_field diy_img" v-else-if="!form['upload_photos'] && $check_field('add','upload_photos')">
							<label for="form_img_upload_photos">
								<div class="btn_add_img">
									<span>+</span>
								</div>
							</label>
						</div>
						<!-- 查询权限 -->
						<div class="diy_field diy_img" v-else-if="$check_field('get','upload_photos')">
							<img :src="$fullUrl(form['upload_photos'])" />
						</div>
					</div>
					<div v-if="$check_field('set','registration_status') || $check_field('add','registration_status') || $check_field('get','registration_status')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>报名状态:
							</span>
						</div>
						<!-- 选项 -->
						<div class="diy_field diy_down">
							<select id="form_registration_status" v-model="form['registration_status']" v-if="(form['registration_status'] && $check_field('set','registration_status')) || (!form['registration_status'] && $check_field('add','registration_status'))" >
								<option v-for="o in list_registration_status" :value="o">
									{{o}}
								</option>
							</select>
							<span v-else-if="$check_field('get','registration_status')">{{ form['registration_status'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','reply_message') || $check_field('add','reply_message') || $check_field('get','reply_message')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>回复信息:
							</span>
						</div>
						<!-- 多文本 -->
						<div class="diy_field diy_desc">
							<textarea id="form_reply_message" v-model="form['reply_message']" v-if="(form['reply_message'] && $check_field('set','reply_message')) || (!form['reply_message'] && $check_field('add','reply_message'))" :disabled="disabledObj['reply_message_isDisabled']" />
							<span v-else-if="$check_field('get','reply_message')">{{ form['full_name'] }}</span>
						</div>
					</div>
				</div>
				<div class="diy_edit_submit_box row">
					<div class="col-12">
						<div class="btn_box">
							<button class="btn_submit" @click="submit()">提交</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import mixin from "@/mixins/page.js";
	export default {
		mixins: [mixin],
		components: {},
		data() {
			return {
				url_get_obj: "~/api/online_registration/get_obj?",
				url_add: "~/api/online_registration/add?",
				url_set: "~/api/online_registration/set?",

				// 登录权限
				oauth: {
					"signIn": true,
					"user_group": []
				},

				// 查询条件
				query: {
					"candidate_information": 0,
					"full_name": "",
					"gender": "",
					"student_number": "",
					"faculty": "",
					"registration_level": "",
					"upload_photos": "",
					"registration_status": "",
					"reply_message": "",
					"online_registration_id": 0,
				},

				obj: {
					"candidate_information": 0, // 考生信息
					"full_name":'', // 姓名
					"gender":'', // 性别
					"student_number":'', // 学号
					"faculty":'', // 院系
					"registration_level":'', // 报考等级
					"upload_photos":'', // 上传照片
					"registration_status":'', // 报名状态
					"reply_message":'', // 回复信息
					"online_registration_id": 0,
				},

				// 表单字段
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
					"online_registration_id": 0,

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
				//报考等级选项列表
				list_registration_level: ['一级','二级','三级'],
				//报名状态选项列表
				list_registration_status: ['报名失败','报名成功'],

				// ID字段
				field: "online_registration_id",
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
			async get_user_session_candidate_information(){
				var _this = this;
				var json = await this.$get("~/api/user_group/get_obj?name=考生用户");
				if(json.result && json.result.obj){
					var source_table = json.result.obj.source_table;
					var user_id = _this.$store.state.user.user_id;
					if (user_id){
						var url = "~/api/"+source_table+"/get_obj?"
						this.$get(url, {"user_id":_this.$store.state.user.user_id}, function(res) {
							if (res.result && res.result.obj) {
								var arr = []
								for (let key in res.result.obj) {
									arr.push(key)
								}
								var arrForm = []
								for (let key in _this.form) {
									arrForm.push(key)
								}
								_this.form["candidate_information"] = user_id
								_this.disabledObj['candidate_information' + '_isDisabled'] = true
								for (var i=0;i<arr.length;i++){
									for (var j=0;j<arrForm.length;j++){
										if (arr[i]===arrForm[j]){
											if (arr[i]!=="candidate_information") {
												_this.form[arrForm[j]] = res.result.obj[arr[i]]
												_this.disabledObj[arrForm[j] + '_isDisabled'] = true
												break;
											}
										}
									}
								}
							}
						});
					}
				}
				else if(json.error){
					console.error(json.error);
				}
			},

			/**
			 * 修改文件
			 * @param { Object } files 上传文件对象
			 * @param { String } str 表单的属性名
			 */
			change_file(files, str) {
				var form = new FormData();
				form.append("file", files[0]);
				this.$post("~/api/online_registration/upload?", form, (res) => {
					if (res.result) {
						this.form[str] = res.result.url;
					} else if (res.error) {
						this.$toast(res.error.message);
					}
				});
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_before(param){
				var form = $.db.get("form");
				if (form) {
					this.obj = $.push(this.obj ,form);
					this.form = $.push(this.form ,form);
				}
				var arr = []
				for (let key in form) {
					arr.push(key)
				}
				for (var i=0;i<arr.length;i++){
					this.disabledObj[arr[i] + '_isDisabled'] = true
				}
				return param;
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json ,func){
				var form = $.db.get("form");
				var obj = Object.assign({} ,form ,this.obj);
				if (form) {
					this.obj = $.push(this.obj ,obj);
				}
				if (form) {
					this.form = $.push(this.form ,form);
				}
				if(func){
					func(json);
				}
			},

		},
		created() {
			this.get_user_session_candidate_information();
			this.get_list_user_candidate_information();
		}
	}
</script>

<style>
</style>
