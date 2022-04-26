<template>
    <div class="hello">
        <!-- 面包屑导航 -->
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/welcome' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>创建问卷</el-breadcrumb-item>
        </el-breadcrumb>
        <el-card style="width: 50%;margin-left: 25%">
            <el-form ref="modelForm" :rule="rules" :model="modelForm" label-position="right" label-width="100px">
                <el-form-item label="主题:">
                    <el-input v-model="modelForm.title" style="width: 300px"></el-input>
                </el-form-item>
                <vuedraggable v-model="modelForm.topic" class="wrapper" @end="end">
                    <div v-for="(item, index) in modelForm.topic" :key="index">
                        <el-collapse>
                            <el-collapse-item>
                                <template slot="title">
                                    第{{ index+1 }}题,题目:{{ item.questionName }}
                                </template>
                                <!-- 问题 -->
                                <el-form-item
                                        :prop="`topic.${index}.type`"
                                        :label="`问题${index + 1}类型`"
                                        :rules="{ required: true, message: '请选择问题类型', trigger: 'change' }"
                                >
                                    <el-radio-group v-model="item.type">
                                        <el-radio :label="0">单选题</el-radio>
                                    </el-radio-group>
                                </el-form-item>
                                <!--              //问题-->
                                <el-form-item
                                        :prop="`topic.${index}.questionName`"
                                        label="问题"
                                        :rules="{ required: true, message: '请填写问题', trigger: 'change' }"
                                >
                                    <el-input v-model.trim="item.questionName" style="width:258px" clearable placeholder="请填写问题" />
                                </el-form-item>
                                <!-- 答案 -->
                                <el-form-item
                                        v-for="(opt, idx) in item.answers"
                                        v-if="item.type!=2"
                                        :key="idx"
                                        :label="`选项${idx + 1}`"
                                        :prop="`topic.${index}.answers.${idx}.value`"
                                        :rules="[
                  { required: true, message: '请输入答案', trigger: 'blur' },
                ]"
                                >
                                    <el-input v-model.trim="opt.value" style="width:258px" clearable placeholder="请输入答案" />
                                    <el-button style="margin-left: 20px" type="danger" icon="el-icon-delete"  size="mini" @click.prevent="removeDomain(index,idx)">删除</el-button>
                                </el-form-item>
                                <el-form-item>
                                    <el-button v-show="item.type!=2" type="warning" size="mini" round @click="addDomain(index)">新增选项</el-button>
                                    <el-button @click="removeQuestion(index)" type="danger" size="mini" round>删除题目</el-button>
                                </el-form-item>
                            </el-collapse-item>
                        </el-collapse>
                    </div>
                </vuedraggable>
                <el-form-item>
                    <el-button @click="addQuestion" type="primary" >新增题目</el-button>
                    <el-button style="margin-top: 10px" type="success" @click="addSubmit()">提交</el-button>
                    <el-button   type="warning" @click="resetForm('modelForm')">重置</el-button>
                </el-form-item>
            </el-form>
        </el-card>
    </div>

</template>
<script>
    import vuedraggable from 'vuedraggable'
    export default {
        name: 'HelloWorld',
        components: {
            vuedraggable
        },
        data() {
            return {
                rules: {},
                modelForm: {
                    title:"请输入问卷主题",
                    topic: [{
                        username:"",
                        major:'',
                        type: '',
                        questionName: '',
                        answers: [{ value: '' }]
                    }, {
                        username:"",
                        major:'',
                        type: '',
                        questionName: '',
                        answers: [{ value: '' }]
                    }]
                },
                topics: [{
                    title:"",
                    username:"",
                    major:'',
                    type: '',
                    questionName: '',
                    answers: [{ value: '' }]
                }, {
                    title:"",
                    username:"",
                    major:'',
                    type: '',
                    questionName: '',
                    answers: [{ value: '' }]
                }]
            }
        },
        methods: {
            end(evt) {
                this.$refs.modelForm.clearValidate()
            },
            removeDomain(index, idx) { // 删除选项
                this.modelForm.topic[index].answers.splice(idx, 1)
            },
            removeQuestion(index) {//删除题目
                this.modelForm.topic.splice(index, 1)
            },
            addDomain(index) { // 新增选项
                this.modelForm.topic[index].answers.push({ value: '' })
            },
            addQuestion() { // 新增题目
                this.modelForm.topic.push({  title:"",username:'', major:'', type: '', questionName: '', answers: [{ value: '' }] })
            },
            resetForm(formName) { // 重置
                this.$refs[formName].resetFields()
            },
            addSubmit() {
                this.$refs.modelForm.validate(valid => {
                    if (valid) {
                      this.addPaper();
                    }
                })
            },

            async addPaper() {

                for(let i = 0;i<this.modelForm.topic.length;i++){
                    this.modelForm.topic[i].title = this.modelForm.title;
                    this.modelForm.topic[i].major = window.sessionStorage.getItem("major");
                    this.modelForm.topic[i].username = window.sessionStorage.getItem("user");
                }
                const {data: res} = await this.$http.post("api/paper/addPaper" ,this.modelForm.topic)
                if (res.code == 1) {
                    this.modelForm.topic = this.topics;//重置数据
                    this.$message.success(res.msg);
                } else {
                    this.$message.error(res.msg);
                }
            }
        }
    }
</script>

<style scoped>

</style>
