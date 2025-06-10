<template>
  <div class="page_search">
	<div class="warp">
	  <div class="container">
		<div class="row">
		  <div class="col-12">
			<div class="card_result_search">
			  <div class="title">搜索结果</div>
				<!-- 文章搜索结果 -->
			  <list_result_search
				:list="result_article"
				title="学校资讯"
				source_table="article"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/candidate_user/list', 'get')"
				:list="result_candidate_user_full_name"
				title="考生用户姓名"
				source_table="candidate_user"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/candidate_user/list', 'get')"
				:list="result_candidate_user_gender"
				title="考生用户性别"
				source_table="candidate_user"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/candidate_user/list', 'get')"
				:list="result_candidate_user_student_number"
				title="考生用户学号"
				source_table="candidate_user"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/online_registration/list', 'get')"
				:list="result_online_registration_full_name"
				title="在线报名姓名"
				source_table="online_registration"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/online_registration/list', 'get')"
				:list="result_online_registration_faculty"
				title="在线报名院系"
				source_table="online_registration"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/online_registration/list', 'get')"
				:list="result_online_registration_registration_level"
				title="在线报名报考等级"
				source_table="online_registration"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/online_registration/list', 'get')"
				:list="result_online_registration_registration_status"
				title="在线报名报名状态"
				source_table="online_registration"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/admission_information/list', 'get')"
				:list="result_admission_information_faculty"
				title="准考证信息院系"
				source_table="admission_information"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/admission_information/list', 'get')"
				:list="result_admission_information_registration_level"
				title="准考证信息报考等级"
				source_table="admission_information"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/admission_information/list', 'get')"
				:list="result_admission_information_examinee_number_"
				title="准考证信息考生考号"
				source_table="admission_information"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/admission_information/list', 'get')"
				:list="result_admission_information_centre_name_"
				title="准考证信息考场名称"
				source_table="admission_information"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/registration_process/list', 'get')"
				:list="result_registration_process_title_name"
				title="报名流程标题名称"
				source_table="registration_process"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/department_management/list', 'get')"
				:list="result_department_management_department_name"
				title="院系管理院系名称"
				source_table="department_management"
			  ></list_result_search>
			</div>
		  </div>
		</div>
	  </div>
	</div>
  </div>
</template>

<script>
import mixin from "../../mixins/page.js";
import list_result_search from "../../components/diy/list_result_search.vue";

export default {
  mixins: [mixin],
  data() {
	return {
	  "query": {
		word: "",
	  },
	  "result_article": [],
			"result_candidate_user_full_name":[],
			"result_candidate_user_gender":[],
			"result_candidate_user_student_number":[],
			"result_online_registration_full_name":[],
			"result_online_registration_faculty":[],
			"result_online_registration_registration_level":[],
			"result_online_registration_registration_status":[],
			"result_admission_information_faculty":[],
			"result_admission_information_registration_level":[],
			"result_admission_information_examinee_number_":[],
			"result_admission_information_centre_name_":[],
			"result_registration_process_title_name":[],
			"result_department_management_department_name":[],
	};
  },
  methods: {
	/**
	 * 获取文章
	 */
	get_article() {
	  this.$get("~/api/article/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_article = json.result.list;
		}
	  });
	},
	/**
	 * 获取full_name
	 */
	get_candidate_user_full_name(){
		this.$get("~/api/candidate_user/get_list?like=0", { page: 1, size: 10, "full_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_candidate_user_full_name = json.result.list;
			result_candidate_user_full_name.map(o => o.title = o['full_name'])
	  			this.result_candidate_user_full_name = result_candidate_user_full_name
		 	}
		});
	},
	/**
	 * 获取gender
	 */
	get_candidate_user_gender(){
		this.$get("~/api/candidate_user/get_list?like=0", { page: 1, size: 10, "gender": this.query.word }, (json) => {
		  if (json.result) {
			var result_candidate_user_gender = json.result.list;
			result_candidate_user_gender.map(o => o.title = o['gender'])
	  			this.result_candidate_user_gender = result_candidate_user_gender
		 	}
		});
	},
	/**
	 * 获取student_number
	 */
	get_candidate_user_student_number(){
		this.$get("~/api/candidate_user/get_list?like=0", { page: 1, size: 10, "student_number": this.query.word }, (json) => {
		  if (json.result) {
			var result_candidate_user_student_number = json.result.list;
			result_candidate_user_student_number.map(o => o.title = o['student_number'])
	  			this.result_candidate_user_student_number = result_candidate_user_student_number
		 	}
		});
	},
	/**
	 * 获取full_name
	 */
	get_online_registration_full_name(){
		this.$get("~/api/online_registration/get_list?like=0", { page: 1, size: 10, "full_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_online_registration_full_name = json.result.list;
			result_online_registration_full_name.map(o => o.title = o['full_name'])
	  			this.result_online_registration_full_name = result_online_registration_full_name
		 	}
		});
	},
	/**
	 * 获取faculty
	 */
	get_online_registration_faculty(){
		this.$get("~/api/online_registration/get_list?like=0", { page: 1, size: 10, "faculty": this.query.word }, (json) => {
		  if (json.result) {
			var result_online_registration_faculty = json.result.list;
			result_online_registration_faculty.map(o => o.title = o['faculty'])
	  			this.result_online_registration_faculty = result_online_registration_faculty
		 	}
		});
	},
	/**
	 * 获取registration_level
	 */
	get_online_registration_registration_level(){
		this.$get("~/api/online_registration/get_list?like=0", { page: 1, size: 10, "registration_level": this.query.word }, (json) => {
		  if (json.result) {
			var result_online_registration_registration_level = json.result.list;
			result_online_registration_registration_level.map(o => o.title = o['registration_level'])
	  			this.result_online_registration_registration_level = result_online_registration_registration_level
		 	}
		});
	},
	/**
	 * 获取registration_status
	 */
	get_online_registration_registration_status(){
		this.$get("~/api/online_registration/get_list?like=0", { page: 1, size: 10, "registration_status": this.query.word }, (json) => {
		  if (json.result) {
			var result_online_registration_registration_status = json.result.list;
			result_online_registration_registration_status.map(o => o.title = o['registration_status'])
	  			this.result_online_registration_registration_status = result_online_registration_registration_status
		 	}
		});
	},
	/**
	 * 获取faculty
	 */
	get_admission_information_faculty(){
		this.$get("~/api/admission_information/get_list?like=0", { page: 1, size: 10, "faculty": this.query.word }, (json) => {
		  if (json.result) {
			var result_admission_information_faculty = json.result.list;
			result_admission_information_faculty.map(o => o.title = o['faculty'])
	  			this.result_admission_information_faculty = result_admission_information_faculty
		 	}
		});
	},
	/**
	 * 获取registration_level
	 */
	get_admission_information_registration_level(){
		this.$get("~/api/admission_information/get_list?like=0", { page: 1, size: 10, "registration_level": this.query.word }, (json) => {
		  if (json.result) {
			var result_admission_information_registration_level = json.result.list;
			result_admission_information_registration_level.map(o => o.title = o['registration_level'])
	  			this.result_admission_information_registration_level = result_admission_information_registration_level
		 	}
		});
	},
	/**
	 * 获取examinee_number_
	 */
	get_admission_information_examinee_number_(){
		this.$get("~/api/admission_information/get_list?like=0", { page: 1, size: 10, "examinee_number_": this.query.word }, (json) => {
		  if (json.result) {
			var result_admission_information_examinee_number_ = json.result.list;
			result_admission_information_examinee_number_.map(o => o.title = o['examinee_number_'])
	  			this.result_admission_information_examinee_number_ = result_admission_information_examinee_number_
		 	}
		});
	},
	/**
	 * 获取centre_name_
	 */
	get_admission_information_centre_name_(){
		this.$get("~/api/admission_information/get_list?like=0", { page: 1, size: 10, "centre_name_": this.query.word }, (json) => {
		  if (json.result) {
			var result_admission_information_centre_name_ = json.result.list;
			result_admission_information_centre_name_.map(o => o.title = o['centre_name_'])
	  			this.result_admission_information_centre_name_ = result_admission_information_centre_name_
		 	}
		});
	},
	/**
	 * 获取title_name
	 */
	get_registration_process_title_name(){
		this.$get("~/api/registration_process/get_list?like=0", { page: 1, size: 10, "title_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_registration_process_title_name = json.result.list;
			result_registration_process_title_name.map(o => o.title = o['title_name'])
	  			this.result_registration_process_title_name = result_registration_process_title_name
		 	}
		});
	},
	/**
	 * 获取department_name
	 */
	get_department_management_department_name(){
		this.$get("~/api/department_management/get_list?like=0", { page: 1, size: 10, "department_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_department_management_department_name = json.result.list;
			result_department_management_department_name.map(o => o.title = o['department_name'])
	  			this.result_department_management_department_name = result_department_management_department_name
		 	}
		});
	},

  },
  components: { list_result_search },
	created(){
    this.query.word = this.$route.query.word || "";
  },
  mounted() {
	this.get_article();
		this.get_candidate_user_full_name();
		this.get_candidate_user_gender();
		this.get_candidate_user_student_number();
		this.get_online_registration_full_name();
		this.get_online_registration_faculty();
		this.get_online_registration_registration_level();
		this.get_online_registration_registration_status();
		this.get_admission_information_faculty();
		this.get_admission_information_registration_level();
		this.get_admission_information_examinee_number_();
		this.get_admission_information_centre_name_();
		this.get_registration_process_title_name();
		this.get_department_management_department_name();
  },
  watch: {
	$route() {
	  $.push(this.query, this.$route.query);
	  this.get_article();
	  this.get_candidate_user_full_name();
	  this.get_candidate_user_gender();
	  this.get_candidate_user_student_number();
	  this.get_online_registration_full_name();
	  this.get_online_registration_faculty();
	  this.get_online_registration_registration_level();
	  this.get_online_registration_registration_status();
	  this.get_admission_information_faculty();
	  this.get_admission_information_registration_level();
	  this.get_admission_information_examinee_number_();
	  this.get_admission_information_centre_name_();
	  this.get_registration_process_title_name();
	  this.get_department_management_department_name();
	},
  },
};
</script>

<style scoped>
.card_search {
  text-align: center;
}
.card_result_search>.title {
  text-align: center;
  padding: 10px 0;
}
</style>
