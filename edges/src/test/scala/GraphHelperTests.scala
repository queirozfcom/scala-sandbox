import com.queirozf.helpers.GraphHelper
import com.queirozf.models.{DirectedEdge, DistanceToNode, Node}
import org.scalatest.{FlatSpec, Matchers}

import scala.collection.mutable

/**
  * Created by felipe on 31/07/17.
  */
class GraphHelperTests extends FlatSpec with Matchers {

  "a simple description using hardcoded directed edges" should "return the expected nodes" in {

    val edges = List(DirectedEdge("2", "3"), DirectedEdge("3", "4"), DirectedEdge("4", "5"))

    val edgeIndex = GraphHelper.buildOutgoingEdgeIndex(edges)

    val distances = GraphHelper.getDistances("2", edgeIndex)

    assert(List(DistanceToNode("3", 1), DistanceToNode("4", 2), DistanceToNode("5", 3)).toSet == distances.toSet)

  }

  "a simple description using hardcoded directed edges, with cycles" should "return the expected nodes" in {

    val edges = List(DirectedEdge("2", "3"), DirectedEdge("3", "4"), DirectedEdge("4", "3"))

    val edgeIndex = GraphHelper.buildOutgoingEdgeIndex(edges)

    val distances = GraphHelper.getDistances("2", edgeIndex)

    assert(List(DistanceToNode("3", 1), DistanceToNode("4", 2)).toSet == distances.toSet)

  }

  "a simple description" should "return the expected nodes" in {

    val lines = List("2 3", "3 4")

    val edges = GraphHelper.buildDirectedEdges(lines)

    val edgeIndex = GraphHelper.buildOutgoingEdgeIndex(edges)

    val distances = GraphHelper.getDistances("2", edgeIndex)

    assert(distances.toSet == List(DistanceToNode("3", 1), DistanceToNode("4", 2)).toSet)

  }

  "a slightly more complex description" should "return the expected nodes" in {

    val lines = List("2 3", "3 4", "4 5")

    val edges = GraphHelper.buildDirectedEdges(lines)

    val edgeIndex = GraphHelper.buildOutgoingEdgeIndex(edges)

    assert(
      GraphHelper.getDistances("2", edgeIndex).toSet == List(DistanceToNode("3", 1),
                                                             DistanceToNode("4", 2),
                                                             DistanceToNode("5", 3)).toSet)
    assert(
      GraphHelper.getDistances("3", edgeIndex).toSet == List(DistanceToNode("2", 1),
                                                             DistanceToNode("4", 1),
                                                             DistanceToNode("5", 2)).toSet)
    assert(
      GraphHelper.getDistances("4", edgeIndex).toSet == List(DistanceToNode("2", 2),
                                                             DistanceToNode("3", 1),
                                                             DistanceToNode("5", 1)).toSet)
    assert(
      GraphHelper.getDistances("5", edgeIndex).toSet == List(DistanceToNode("2", 3),
                                                             DistanceToNode("3", 2),
                                                             DistanceToNode("4", 1)).toSet)

  }

  "a graph" should "be updated with shorter distances if new edges are added" in {
    val lines = List("2 3", "3 4", "2 4")

    val edges = GraphHelper.buildDirectedEdges(lines)

    val edgeIndex = GraphHelper.buildOutgoingEdgeIndex(edges)

    assert(GraphHelper.getDistances("2", edgeIndex).toSet == List(DistanceToNode("3", 1), DistanceToNode("4", 1)).toSet)
    assert(GraphHelper.getDistances("3", edgeIndex).toSet == List(DistanceToNode("2", 1), DistanceToNode("4", 1)).toSet)
    assert(GraphHelper.getDistances("4", edgeIndex).toSet == List(DistanceToNode("2", 1), DistanceToNode("3", 1)).toSet)

  }

  "a graph with cycles" should "not cause infinite loops" in {

    val lines = List("2 3", "3 4", "4 2")

    val edges = GraphHelper.buildDirectedEdges(lines)

    val edgeIndex = GraphHelper.buildOutgoingEdgeIndex(edges)

    assert(GraphHelper.getDistances("2", edgeIndex).toSet == List(DistanceToNode("3", 1), DistanceToNode("4", 1)).toSet)
    assert(GraphHelper.getDistances("3", edgeIndex).toSet == List(DistanceToNode("2", 1), DistanceToNode("4", 1)).toSet)
    assert(GraphHelper.getDistances("4", edgeIndex).toSet == List(DistanceToNode("2", 1), DistanceToNode("3", 1)).toSet)
  }

}
