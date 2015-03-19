package org.krazykat.iptables.dsl

/****************************************
 *                                      *
 *              Commands                *
 *                                      *
 ****************************************/
abstract class _Command(/*val command: String*/)

case class Append(table: _Table, chain: _Chain, ruleSpec: RuleSpec) extends _Command(/*s" iptables --table $table --append $chain $ruleSpec "*/)
object Append {
  def apply(chain: _Chain, ruleSpec: RuleSpec): Append = new Append(Filter(), chain, ruleSpec) 
}

case class Check(table: _Table, chain: _Chain, ruleSpec: RuleSpec) extends _Command(/*s" iptables --table $table --check $chain $ruleSpec "*/)
object Check {
  def apply(chain: _Chain, ruleSpec: RuleSpec): Check = new Check(Filter(), chain, ruleSpec) 
}

case class Delete(table: _Table, chain: _Chain, rule: _Rule) extends _Command(/*s" iptables --table $table --delete $chain $rule "*/)
object Delete {
  def apply(chain: _Chain, rule: _Rule): Delete = new Delete(Filter(), chain, rule)   
}

case class Replace(table: _Table, chain: _Chain, ruleNum: RuleNum, ruleSpec: RuleSpec) extends _Command(/*s" iptables --table $table --delete $chain $ruleNum $ruleSpec "*/)
object Replace {
  def apply(chain: _Chain, ruleNum: RuleNum, ruleSpec: RuleSpec): Replace = new Replace(Filter(), chain, ruleNum, ruleSpec)  
}

case class Insert(table: _Table, chain: _Chain, ruleNum: RuleNum, ruleSpec: RuleSpec) extends _Command(/*s" iptables --table $table --insert $chain $ruleNum $ruleSpec "*/)
object Insert {
  def apply(chain: _Chain, ruleSpec: RuleSpec): Insert = new Insert(Filter(), chain, RuleNum(1), ruleSpec) 
  def apply(table: _Table, chain: _Chain, ruleSpec: RuleSpec): Insert = new Insert(table, chain, RuleNum(1), ruleSpec) 
}

case class Flush(table: _Table, chain: Option[_Chain]) extends _Command(/*s" iptables --table $table --flush $chain "*/)
object Flush {
//  def apply(table: _Table, chain: _Chain): Flush = new Flush(table, Some(chain))
  def apply(chainOpt: Option[_Chain]): Flush = new Flush(Filter(), chainOpt)
//  def apply(): Flush = new Flush(Filter(), None) /*{ override val command = s" iptables --table $table --flush " }*/
}

case class Zero(table: _Table, chainOpt: Option[_Chain] = None, ruleNumOPt: Option[RuleNum] = None) extends _Command(/*s" iptables --table $table --zero $chain $ruleNum "*/)
object Zero {
//  def apply(table: _Table, chain: _Chain, ruleNum: RuleNum): Zero = new Zero(table, Some(chain), Some(ruleNum))
  def apply(chainOpt: Option[_Chain], ruleNumOpt: Option[RuleNum]): Zero = new Zero(Filter(), chainOpt, ruleNumOpt)
  def apply(chainOpt: Option[_Chain]): Zero = new Zero(Filter(), chainOpt, None)
//  def apply(chain: _Chain): Zero = new Zero(Filter(), Some(chain), None) /* { override val command = s" iptables --table $table --zero $chain " }*/
//  def apply(table: _Table, chain: _Chain): Zero = new Zero(table, Some(chain), None)  /*{ override val command = s" iptables --table $table --zero $chain " }*/
//  def apply(table: _Table): Zero = new Zero(table, None, None) /*{ override val command = s" iptables --table $table --zero " }*/
//  def apply(): Zero = new Zero(Filter(), None, None) /*{ override val command = s" iptables --table $table --zero " }*/
}
