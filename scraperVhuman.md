# 🗺️ Project Roadmap: Human vs. Scraper Real-Time Counter

This roadmap breaks down the construction of a dual-counter tracking system using **React**, **Vercel Serverless Functions**, and **Vercel KV (Redis)**. 

---

## 🛠️ Tech Stack Architecture
*   **Frontend**: React (hosted on Vercel)
*   **Backend**: Vercel Serverless Functions (Node.js API routes)
*   **Database**: Vercel KV (Free Tier Redis)

---

## 📅 Phase 1: Database Setup
**Estimated Time**: 1–2 Hours
**Goal**: Create a free, volatile-fast storage layer to hold `human_count` and `bot_count`.

### Tasks
- [ ] Log into your Vercel Dashboard and navigate to the **Storage** tab.
- [ ] Create a new **KV Database** (powered by Redis).
- [ ] Link the KV database to your project repository to auto-inject environment variables (`KV_URL`, `KV_REST_API_TOKEN`).
- [ ] Run `npm install @vercel/kv` in your React project directory.

### Technical Resources
*   [Vercel Marketplace: Upstash for Redis](https://vercel.com/marketplace/upstash)
*   [@upstash/redis npm Documentation](https://www.npmjs.com/package/@upstash/redis)

---

## 🌐 Phase 2: Backend API Endpoints
**Estimated Time**: 3–4 Hours
**Goal**: Write secure serverless endpoints so your frontend can read and update database values.

### Tasks
- [ ] Create a `api/counters.js` file (or `.ts`) to handle `GET` requests. It must read and return `human_count` and `bot_count`.
- [ ] Create a `api/increment.js` file to handle `POST` requests.
- [ ] Secure the `POST` endpoint so it only accepts valid payloads (e.g., `{ type: 'human' }` or `{ type: 'scraper' }`).
- [ ] Implement the Redis atomic increment command (`kv.incr('key_name')`) inside the backend functions.

### Technical Resources
*   [Vercel Functions Docs](https://vercel.com/docs/functions)
*   [Redis INCR Command Reference](https://redis.io/commands/incr/)

---

## 🕵️‍♂️ Phase 3: The Detection Engine
**Estimated Time**: 4–6 Hours
**Goal**: Build a multi-layered classification system to separate bots from humans.

### Tasks
- [ ] **The Honeypot (HTML Layer)**: Create an invisible link in your React DOM `<a href="/api/increment?type=scraper">` hidden via CSS absolute positioning off-screen.
- [ ] **Interaction Trigger (JS Layer)**: Set up a React `useEffect` hook that listens for the first `mousemove`, `scroll`, or `touchstart` event before firing a `fetch()` to `/api/increment` for humans.
- [ ] **ASN Verification (Network Layer)**: Parse the client IP inside your serverless function and pass it to a free lookup API to check if it originates from an enterprise cloud provider (AWS, DigitalOcean, Hetzner). Override the entry to a scraper counter if true.

### Technical Resources
*   [MDN: Element mousemove Event](https://developer.mozilla.org/en-US/docs/Web/API/Element/mousemove_event)
*   [ip-api.com JSON Documentation](https://ip-api.com/docs/api:json)

---

## 🎨 Phase 4: UI Dashboard & Polling
**Estimated Time**: 2–3 Hours
**Goal**: Pull everything together into a neat visual presentation on your website.

### Tasks
- [ ] Create a React component to display the statistics elegantly (e.g., two large dashboard cards).
- [ ] Implement a `useEffect` hook to fetch initial counts using your `GET /api/counters` endpoint on component mount.
- [ ] **Optional Real-Time Tick**: Use a safe `setInterval` poller to update the count numbers every 5 to 10 seconds.
- [ ] Run `git push` to deploy the final build directly to Vercel production.

### Technical Resources
*   [React Docs: Synchronizing with Effects](https://react.dev/learn/synchronizing-with-effects)
*   [MDN: Window setInterval()](https://developer.mozilla.org/en-US/docs/Web/API/Window/setInterval)

---

## 💡 Pro-Tips for Success
1. **Remove Event Listeners**: Always use `window.removeEventListener` immediately after a human is confirmed so you don't accidentally spam your own database API with every twitch of a user's mouse.
2. **Local Development**: Copy your Vercel KV environment variables into a local `.env.local` file so you can safely build and test everything locally using `vercel dev`.
