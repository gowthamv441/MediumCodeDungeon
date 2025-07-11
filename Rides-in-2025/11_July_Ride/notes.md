### 🔍 Learnings:
- Subsets are best handled using backtracking.
- Always `add(new ArrayList<>(subset))` to avoid mutation issues.
- Recursion can be simplified by passing index and controlling loop.

### 🧠 Mistakes Made:
- Initially added `subset` directly, causing all entries in result to mutate.
