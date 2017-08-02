package com.queirozf

/**
  * Created by felipe on 31/07/17.
  */
package object models {

  case class DirectedEdge(fromLabel: String, toLabel: String)

  case class Edge(label1: String, label2: String)

  case class Node(label: String, neighbours: scala.collection.mutable.Map[String, Int])

  object Node {
    def apply(label: String): Node = Node(label, scala.collection.mutable.Map.empty[String, Int])

  }

  case class DistanceToNode(targetLabel: String, value: Int)

}
