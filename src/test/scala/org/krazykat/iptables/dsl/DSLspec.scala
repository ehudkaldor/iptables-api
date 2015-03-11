package org.krazykat.iptables.dsl

import org.specs2.mutable.Specification

class DSLspec extends Specification {
  
  "Generate iptables commands" should {
    "generate delete command with no table and rulenum 5" in {
      val command = Delete(Input(), RuleNum(5))
      command.command.replaceAll("\\s+"  , " ") must be equalTo(" iptables -t filter --delete INPUT 5 ")
    }
    "generate delete command with table nat and rulenum 10" in {
      val command = Delete(Nat(), Output(), RuleNum(10))
      command.command.replaceAll("\\s+"  , " ") must be equalTo(" iptables -t nat --delete OUTPUT 10 ")
    }
  }
  
  "Generate flush commands" should {
    "generate flush command with table nat and chain INPUT" in {
      val command = Flush(Nat(), Input())
      command.command.replaceAll("\\s+"  , " ") must be equalTo(" iptables -t nat INPUT --flush ")
    }
    "generate flush command with no table and chain FORWARD" in {
      val command = Flush(Forward())
      command.command.replaceAll("\\s+"  , " ") must be equalTo(" iptables -t filter FORWARD --flush ")
    }
    "generate flush command with no table and no chain" in {
      val command = Flush()
      command.command.replaceAll("\\s+"  , " ") must be equalTo(" iptables -t filter --flush ")
    }
  }
  
  "Generate zero commands" should {
    "generate zero command with table nat and chain INPUT" in {
      val command = Zero(Nat(), Input())
      command.command.replaceAll("\\s+"  , " ") must be equalTo(" iptables -t nat INPUT --flush ")
    }
    "generate flush command with no table and chain FORWARD" in {
      val command = Flush(Forward())
      command.command.replaceAll("\\s+"  , " ") must be equalTo(" iptables -t filter FORWARD --flush ")
    }
    "generate flush command with no table and no chain" in {
      val command = Flush()
      command.command.replaceAll("\\s+"  , " ") must be equalTo(" iptables -t filter --flush ")
    }
  }
}