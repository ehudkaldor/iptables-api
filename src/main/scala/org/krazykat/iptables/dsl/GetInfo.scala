package org.krazykat.iptables.dsl

/************************************************
 *                                              *
 *              Get Info                        *
 *              List (-L), List_Rules (-S)      *
 *                                              *
 ************************************************/
abstract class _GetInfo(val command: String) { override def toString = command}

case class List(table: _Table, chain: _Chain) extends _GetInfo(s" iptables --table $table --list $chain")
object List {
  def apply(chain: _Chain): List = new List(Filter(), chain)
  def apply(table: _Table): List = new List(table, null) { override val command = s" iptables --table $table --list " }
  def apply(): List = new List(Filter(), null) { override val command = s" iptables --table $table --list " }
}

case class ListRules(table: _Table, chain: _Chain) extends _GetInfo(s" iptables --table $table --list-rules $chain")
object ListRules {
  def apply(chain: _Chain): ListRules = new ListRules(Filter(), chain)
  def apply(table: _Table): ListRules = new ListRules(table, null) { override val command = s" iptables --table $table --list " }
  def apply(): ListRules = new ListRules(Filter(), null) { override val command = s" iptables --table $table --list " } 
}
