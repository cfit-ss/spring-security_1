package com.shf.jwt.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 *
 * @Author shengs
 * @Date 2022/11/02
 * @Version V1.0
 **/
@Data
@Entity
@Table(name = "t_user")
public class TUser {
	/**
	 * 主键
	 */
	@Id
	private Integer id;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 用户角色
	 */
	private String salt;
	/**
	 * 账号状态
	 */
	private String state;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 电话
	 */
	private String phone;

	@ManyToMany(fetch= FetchType.EAGER)//立即从数据库中进行加载数据;
	@JoinTable(name = "t_user_role", joinColumns = { @JoinColumn(name = "id") }, inverseJoinColumns ={@JoinColumn(name = "userId") })
	private List<TRole> roleList;// 一个用户具有多个角色
}
