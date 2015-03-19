package org.krazykat.iptables.dsl

object Implicits {
  
  implicit def tableToString(table: _Table): String = {
    table match {
      case Filter() => "filter"
      case Nat() => "nat"
      case Mangle() => "mangle"
      case Raw() => "raw"
      case Security() => "security"
    }
  }
  
  implicit def ruleToString(rule: _Rule): String = {
    rule match {
      case r: RuleNum => r.num.toString()
      case r: RuleSpec => s""
    }
  }
  
//  implicit def ruleSpecToString(ruleSpec: RuleSpec): String = s""

//  implicit def ruleNumToString(ruleNum: RuleNum): String = ruleNum.num.toString

  implicit def chainToString(chain: _Chain): String = {
    chain match {      
      case Input() => "INPUT"
      case Forward() => "FORWARD"
      case Output() => "OUTPUT"
      case a: _Chain => a.name
    }
  }
  
  implicit def createChainToString(newChain: NewChain): String = {
    newChain match {
      case NewChain(None, _) => s"--new-chain ${newChain.chainName}"
      case NewChain(tableOpt, _) => {
        val tableString: String = newChain.tableOpt.get
        s"--table ${tableOpt.get} --new-chain ${newChain.chainName}"
      }  
    }
  }
  
  implicit def deleteChainToString(deleteChain: DeleteChain): String = {
    deleteChain match {
      case DeleteChain(None, _) => s"--delete-chain ${deleteChain.chain.name}"
      case DeleteChain(tableOpt, _) => {
        val tableString: String = deleteChain.tableOpt.get
        s"--table ${tableOpt.get} --delete-chain ${deleteChain.chain.name}"
      }  
    }
  }
  
  implicit def renameChainToString(renameChain: RenameChain): String = {
    renameChain match {
      case RenameChain(None, _, _) => s"--rename-chain ${renameChain.chain.name} ${renameChain.newName}"
      case RenameChain(tableOpt, _, _) => {
        val tableString: String = renameChain.tableOpt.get
        s"--table ${tableOpt.get} --rename-chain ${renameChain.chain.name} ${renameChain.newName}"
      }  
    }
  }
  
  implicit def setPolicyToString(setPolicy: SetPolicy): String = {
    val policyString: String = setPolicy.policy
    val chainString: String = setPolicy.chain
    val tableString: String = setPolicy.table
//    setPolicy match {
//      case SetPolicy(None, _, _) => {
//        s"iptables --policy $chainString $policyString"
//      }
//      case SetPolicy(tableOpt, _, _) => {
        s"iptables --table $tableString --policy $chainString $policyString"

//      }
//    }
  }

  implicit def policyToString(p: _Policy): String = {
    p match {
      case Accept() => "ACCEPT"
      case Drop() => "DROP"
    }
  }
  
  implicit def targetToString(target: _Target): String = {
    target match {
      case Accept() => "ACCEPT"
      case Reject() => "REJECT"
      case Drop() => "DROP"
      case Log() => "LOG"
      case j: Jump => j.dest
    }
  }
  
  implicit def deleteToString(delete: Delete): String = {
    val tableString: String = delete.table
    val ruleString: String = delete.rule
    val chainString: String = delete.chain
//    delete match {
//      case Delete(None,_,_) => s"iptables --delete $chainString $ruleString"
//      case Delete(tableOpt,_,_) => {
        s"iptables --table $tableString --delete $chainString $ruleString"
//      }
//    }
//    "iptables --table " + delete.table + " --delete " + delete.chain + " " + delete.rule
  }
  
  implicit def flushToString(flush: Flush): String = {
    val tableString: String = flush.table
    flush.chain match {
      case None => s"iptables --table $tableString --flush"
      case Some(chain) => {
        val chainString: String = flush.chain.get
        s"iptables --table $tableString --flush $chainString"
      }
    }
//    flush match {
//      case Flush(None,None) => s"iptables --flush"
//      case Flush(None,chainOpt) =>  {
//        val chainString: String = chainOpt.get
//        s"iptables --flush $chainString"
//      }
//      case Flush(tableOpt,chainOpt) =>  {
//        val chainString: String = flush.chain.
//      }
//    }
  }
  
  implicit def zeroToString(zero: Zero): String = {
//    val t = zero.table match {
//      case None => ""
//      case Some(t) => {
//        val tStr:String = t
//        s" --table $tStr"
//      }
//    }
    val tableStr:String = zero.table
    val c = zero.chainOpt match {
      case None => ""
      case Some(c) => {
        val cStr: String = c
        s" $cStr"
      }
    }
    val r = zero.ruleNumOPt match {
      case None => ""
      case Some(r) => {
        val rStr: String = r
        s" $rStr"
      }
    }
    s"iptables --table $tableStr --zero$c$r"
//    zero match {
//      case Zero(None, None, None) => "iptables --zero"
//      case Zero(None, )
//      case Zero(table, None, None) => s"iptables --table ${table} --zero"
//      case Zero(table, chainOpt, None) => {
//        val chainString: String = zero.chainOpt.get
//        s"iptables --table ${table} --zero $chainString"
//      }
//      case Zero(table, chainOpt, ruleNumOpt) => {
//        val ruleString: String = zero.ruleNumOPt.get
//        val chainString: String = zero.chainOpt.get
//        s"iptables --table ${table} --zero $chainString $ruleString"
//      }
//    }
  }
}