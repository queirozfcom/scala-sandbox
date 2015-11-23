package com.queirozf.sandbox

/**
 * Created by felipe on 23/11/15.
 */
object App {

  def main(args: Array[String]) {

    val lst = List(1, 2, 3, 4, 6)
    val target1 = 6

    println("\n")

    println(s"Find the combinations for $lst using $target1 as target")
    println(">>"+getCombinations(lst, target1))

    val source = 1
    val target2 = 4

    val paths = Map(1 -> List(2, 3), 2 -> List(3, 4), 3 -> List(5))

    println("")
    println(s"Find the shortest path that takes one from $source to $target2 given grid: $paths")
    println(">> "+findPath(source, target2, paths))
    println("\n")

  }

  /** **************************************
    * TASK 1
    */

  /**
   *
   * @param input any list of ints
   * @param targetSum target sum
   * @return list of all combinations of input seq that add up to target sum
   */
  def getCombinations(input: List[Int], targetSum: Int): List[List[Int]] = {

    val combinationSizes = input.size.to(1, step = -1)

    combinationSizes.toList.flatMap { combinationSize =>
      input.combinations(combinationSize).filter(combination => combination.sum == targetSum)
    }

  }

  /** **************************************
    * TASK 2
    */

  // some type aliases to make it easier to think about the problem
  type Grid = Map[Int, List[Int]]
  type Path = List[Int]

  // a few extra methods for our grid
  implicit class GridMethods(g: Grid) {
    private def isValidSourceNode(i: Int) = g.keys.toList.contains(i)
    private def isValidTargetNode(i: Int) = g.values.flatten.toList.contains(i)

    def getAvailableTargets(i: Int):List[Int] = g.getOrElse(i,List.empty[Int])
    def isValidNode(i: Int) = this.isValidSourceNode(i) || this.isValidTargetNode(i)
  }

  /**
   * Given source and target nodes and map linking each node to other nodes it can access (a grid), return the shortest path from source to origin
   *
   * @param source
   * @param target
   * @param grid
   * @return maybe the shortest path, if I could find any
   */
  def findPath(source: Int, target: Int, grid: Grid): Option[Path] = {

    if (grid.isValidNode(source) && grid.isValidNode(target)) {

      def walk(currentPath: Path): List[Path] = {
        val lastNode = currentPath.last
        if(lastNode == target) List(currentPath)
        else grid.getAvailableTargets(lastNode).flatMap( aTarget => walk( currentPath :+ aTarget ) )
      }

      // the first element is the source itself
      val paths = walk(List(source))

      // selecting the shortest path
      val shortestPath:Path = if(paths.isEmpty) List.empty[Int] else paths.sortBy(_.size).head

      Some(shortestPath)
    }
    else None

  }
}
