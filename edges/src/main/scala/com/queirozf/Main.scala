package com.queirozf

import com.queirozf.helpers.GraphHelper
import com.queirozf.models.{Edge, Node}

import scala.io.Source

/**
  * Created by felipe on 31/07/17.
  */
object Main extends App {

  val FILE_NAME = "edges.dat"

  val lines = Source.fromResource(FILE_NAME).getLines().toList

  val uniqueNodeLabels = lines.flatMap(line => line.split(" ")).toSet

  val edges = GraphHelper.buildDirectedEdges(lines)

  val edgeIndex = GraphHelper.buildOutgoingEdgeIndex(edges)

  val sorted = uniqueNodeLabels.toList
    .map(nodeLabel => (nodeLabel, GraphHelper.getDistances(nodeLabel, edgeIndex)))
    .map { case (nodeLabel, distances) => (nodeLabel, distances.map(distance => distance.value).sum) }
    .sortBy {
      case (_, distanceSum) => distanceSum
    }
    .map {
      case (nodeLabel, sum) =>
        if (sum == 0) s"label: $nodeLabel, centrality: 0" else s"label: $nodeLabel, centrality: ${1.0 / sum}"
    }

  sorted.foreach(println)

}
